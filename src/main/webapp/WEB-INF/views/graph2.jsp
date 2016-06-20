<html>

<head>
	<title>Metric Graph</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.bundle.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.js"></script>
</head>

<body onload="drawChart()">
	<div style="width: 500px; height: 500px; float:left;">
		<canvas id="myChart" width="50" height="50"></canvas>
	</div>
	<div style="width: 500px; height: 500px; float:right;">
		<canvas id="myPie" width="50" height="50"></canvas>
	</div>
</body>

</html>
<script type="text/javascript">
function drawChart() {

	var bar_title = [];
	var bar_total = [];

	bar_title.push('');
	bar_total.push(0);

	$.get("http://localhost:8080/spring-rest-security/root/metrics/single?user=ashim@ekbana.com", function(jsonData) {

		// var jsonData = '{"user":"ashim","apiLogs":[{"request":"GET/users","datas":[{"status":200,"count":5},{"status":400,"count":6},{"status":500,"count":7}]},{"request":"GET/metrics","datas":[{"status":200,"count":7},{"status":400,"count":5},{"status":500,"count":5}]},{"request":"GET/products","datas":[{"status":200,"count":8},{"status":400,"count":5},{"status":500,"count":6}]},{"request":"GET/customers","datas":[{"status":200,"count":3},{"status":400,"count":9},{"status":500,"count":4}]}]}';

		var api_data = jsonData;

		if (jsonData == "")
			return;

		$.each(api_data.apiLogs, function(name, value) {
			bar_title.push(value.request);

			var total = 0;

			$.each(value.datas, function(data_name, data_value) {
				total += data_value.count;
			});

			bar_total.push(total);
		});

		var ctx = document.getElementById("myChart");
		var data = {
			// labels: bar_title,
			labels: bar_title,
			datasets: [{
				label: "API Call Dataset",
				backgroundColor: "rgba(255,99,132,0.2)",
				borderColor: "rgba(255,99,132,1)",
				borderWidth: 1,
				hoverBackgroundColor: "rgba(255,99,132,0.4)",
				hoverBorderColor: "rgba(255,99,132,1)",
				data: bar_total
			}]
		};

		var myChart = new Chart(ctx, {
			type: "bar",
			data: data,
			options: {
				onClick: function(chart, labelItem) {

					var request = labelItem[0]._model.label;

					var status = [];
					var count = [];

					status.push(0);
					count.push(0);

					$.each(api_data.apiLogs, function(name, value) {

						if (value.request == request) {

							$.each(value.datas, function(data_name, data_value) {
								status.push(data_value.status);
								count.push(data_value.count);
							});
						}
					});

					var ctx = document.getElementById("myPie");
					var data = {
						datasets: [{
							data: count,
							backgroundColor: [
								"#FF6384",
								"#4BC0C0",
								"#FFCE56",
								"#E7E9ED",
								"#36A2EB"
							],
							label: 'My dataset' // for legend
						}],
						labels: status
					};

					var myPie = new Chart(ctx, {
						data: data,
						type: "polarArea",
						options: {
							elements: {
								arc: {
									borderColor: "#000000"
								}
							},
							animation: {
								animateScale: true
							}
						}
					});
				}
			}
		});
	});
}
</script>