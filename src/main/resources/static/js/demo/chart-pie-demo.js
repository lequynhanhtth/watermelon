// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var ten = document.getElementsByName("tensp");
var soluong = document.getElementsByName("soluong");
var tendata = ["Direct", "Referral", "Social"];
var soluongdata = [55, 30, 15];
var i;
for (i = 0; i < ten.length; i++) {
  tendata[i] = ten[i].value;
}
for (i = 0; i < soluong.length; i++) {
  soluongdata[i] = soluong[i].value;
}
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: tendata,
    datasets: [{
      data: soluongdata,
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#511281','#a5e1ad','#4ca1a3','#21094e'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
