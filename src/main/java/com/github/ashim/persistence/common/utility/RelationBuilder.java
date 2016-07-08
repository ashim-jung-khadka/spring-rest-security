package com.github.ashim.persistence.common.utility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class RelationBuilder {

	@SuppressWarnings("unchecked")
	public static <T> List<T> buildCollection(final List<T> objects) {

		int level = 2;

		List<T> nObjects = new ArrayList<>();
		for (Object object : objects) {
			nObjects.add((T) build(object, level));
		}

		return nObjects;
	}

	public static <T> T build(T oObject) {
		int level = 2;

		return build(oObject, level);
	}

	@SuppressWarnings("unchecked")
	private static <T> T build(T oObject, int level) {

		Class<?> oClass = oObject.getClass();
		T nObject = null;

		try {
			nObject = (T) oObject.getClass().newInstance();
			Field[] oFields = oClass.getDeclaredFields();

			for (Field field : oFields) {

				field.setAccessible(true);
				Class<?> targetType = field.getType();
				Object innerClassObject = field.get(oObject);

				if (targetType.equals(List.class)) {
					if (isAvailable(level)) {

						List<Object> oInnerObjects = (List<Object>) innerClassObject;
						List<Object> nInnerObjects = new ArrayList<>();

						for (Object oInnerObject : oInnerObjects) {
							Class<?> objectType = getFieldType(field);

							if (objectType.isAnnotationPresent(Entity.class)) {
								Object obj = build(oInnerObject, level - 1);
								nInnerObjects.add(obj);
							}
						}

						field.set(nObject, nInnerObjects);
					}
				} else if (targetType.equals(Set.class)) {
					if (isAvailable(level)) {

						Set<Object> oInnerObjects = (Set<Object>) innerClassObject;
						Set<Object> nInnerObjects = new HashSet<>();

						for (Object oInnerObject : oInnerObjects) {
							Class<?> objectType = getFieldType(field);

							if (objectType.isAnnotationPresent(Entity.class)) {
								Object obj = build(oInnerObject, level - 1);
								nInnerObjects.add(obj);
							}
						}

						field.set(nObject, nInnerObjects);
					}
				} else {

					Class<?> clazz = innerClassObject.getClass();

					if (clazz.isAnnotationPresent(Entity.class)) {
						if (isAvailable(level)) {

							Object obj = build(innerClassObject, level - 1);
							field.set(nObject, obj);
						}
					} else {
						field.set(nObject, innerClassObject);
					}
				}

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return nObject;

	}

	private static Class<?> getFieldType(Field field) {
		Class<?> targetType = field.getType();

		if (targetType.equals(List.class)) {
			ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
			targetType = (Class<?>) stringListType.getActualTypeArguments()[0];
		}

		return targetType;
	}

	private static boolean isAvailable(int level) {
		if (level - 1 == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @deprecated
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static Object build1(Object object) {

		try {
			Class<?> rootClass = object.getClass();
			List<Field> rootFields = getAnnotatedFields(rootClass);

			for (Field rootField : rootFields) {

				rootField.setAccessible(true);

				Class<?> targetType = rootField.getType();
				Object innerClassObject = rootField.get(object);

				if (targetType.equals(List.class)) {

					@SuppressWarnings("unchecked")
					List<Object> innerClassObjects = (List<Object>) innerClassObject;

					for (Object innerObject : innerClassObjects) {
						Class<?> objectType = getFieldType(rootField);

						if (objectType.isAnnotationPresent(Entity.class)) {
							setInnerRelationObjectToNull(objectType, innerObject);
						}
					}

				} else {
					if (targetType.isAnnotationPresent(Entity.class)) {
						setInnerRelationObjectToNull(targetType, innerClassObject);

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

	/**
	 * @deprecated
	 * @param object
	 * @return
	 */
	@Deprecated
	private static void setInnerRelationObjectToNull(Class<?> objectType, Object object)
			throws IllegalArgumentException, IllegalAccessException {

		List<Field> innerRelations = getAnnotatedFields(objectType);

		for (Field innerRelation : innerRelations) {
			innerRelation.setAccessible(true);
			innerRelation.set(object, null);
		}

	}

	/**
	 * @deprecated
	 * @param object
	 * @return
	 */
	@Deprecated
	private static List<Field> getAnnotatedFields(Class<?> clazz) {

		List<Class<? extends Annotation>> annotations = new ArrayList<>();
		annotations.add(OneToOne.class);
		annotations.add(OneToMany.class);
		annotations.add(ManyToOne.class);
		annotations.add(ManyToMany.class);

		List<Field> result = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			annotations.forEach(annotation -> {
				if (field.isAnnotationPresent(annotation)) {
					result.add(field);
				}
			});
		}

		return result;
	}
}