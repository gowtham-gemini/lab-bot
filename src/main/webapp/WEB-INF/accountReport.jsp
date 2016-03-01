<!DOCTYPE html>
<html ng-app="myApp">

  <head>
    <link href='./css/style.css' rel="stylesheet" type="text/css" />
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/report.js"></script>
  </head>

  <body ng-controller="ReportCtrl">
  	<div ng-class="loading"></div>
	<div class="container theme-showcase" role="main">
      	<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
		  <h1>AWS Accounts Based Report</h1>
		  <p>This report generate account based VMs count and flavor based count.</p>
		  <form ng-submit="loadAcountReport()" class="form-inline">
		     <div class="row">
			     <div class="form-group">
			      <label for="accounts">Access Key:</label>
			      <input type="text" ng-model="accountreport.accessKey" class="form-control" placeholder="Enter AWS Access Key">
			      <label for="accounts">Secreet Key:</label>
			      <input type="text" ng-model="accountreport.secreetKey" class="form-control" placeholder="Enter AWS Secreet Key">
			     </div>
		     </div>
		     <div class="row" style="margin-top: 10px;">
			     <div class="form-group">
			      <label for="accounts">Accounts:</label>
			      <input type="text" ng-model="accountreport.account_name" class="form-control" placeholder="Enter Account Names">
			     </div>
			     <button class="btn btn-primary btn-sm">Generate Report</button>
		     </div>
		  </form>
		  <p2>Enter a comma separated list of user names Eg: user1,user2,user3</p2>
		</div>
		<table class="table table-bordered" style="margin-top:10px">
		     <tr class="active">
		         <th>User Name</th>
		         <th>Flavor Name</th>
		         <th>Count</th>
		     </tr>
		     <tr ng-repeat-start="account in accounts">
		      {{account.acountName}}
		         <td style="font-weight: bold;" colspan="4">
		              {{account.accountName}}
		         </td>
		     </tr>
		     <tr ng-repeat="(key,value) in account">
		     	  <td rowspan="{{account.length}}" colspan="1"></td>
		         <td colspan="1" >{{key}}</td>
		         <td colspan="1" >{{value}}</td>
		     </tr>
		     <tr ng-repeat-end></tr>
		</table>
   	</div>
  </body>
</html>