var app = angular.module('myApp', []);
function ReportCtrl($scope, $http) {
    $scope.accounts = [];
    $scope.regions = [];
    $scope.unUsedVolumes = [];
    $scope.loading = "";
    $scope.accountreport = {};
    $scope.loadAcountReport = function() {
    	$scope.loading="se-pre-con";
    	console.log($scope);
        var httpRequest = $http({
            method: 'GET',
            url: 'http://localhost:8080/aws/usage/accountReport',
            params: {account: $scope.accountreport.account_name, accessKey:$scope.accountreport.accessKey, secreetKey:$scope.accountreport.secreetKey}

        }).success(function(data, status) {
            $scope.accounts = data;
            $scope.loading="se-pre-remove";
        }).error(function(data, status) {
            $scope.loading="se-pre-remove";
            alert(data.message);
        });
    };
    $scope.loadRegionReport = function() {
    	$scope.loading="se-pre-con";
        var httpRequest = $http({
            method: 'GET',
            url: 'http://localhost:8080/aws/usage/region/flavorReport',
            params: {accessKey:$scope.accountreport.accessKey, secreetKey:$scope.accountreport.secreetKey}

        }).success(function(data, status) {
            $scope.regions = data;
            $scope.loading="se-pre-remove";
        }).error(function(data, status) {
            $scope.loading="se-pre-remove";
            alert(data.message);
        });
    };
    $scope.loadVolumeReport = function() {
    	$scope.loading="se-pre-con";
        var httpRequest = $http({
            method: 'GET',
            url: 'http://localhost:8080/aws/volumeReport/unusedVolumes',
            params: {accessKey:$scope.accountreport.accessKey, secreetKey:$scope.accountreport.secreetKey}

        }).success(function(data, status) {
            $scope.unUsedVolumes = data;
            $scope.loading="se-pre-remove";
        }).error(function(data, status) {
            $scope.loading="se-pre-remove";
            alert(data.message);
        });
    };
    $scope.cleanVolume = function() {
    	$scope.loading="se-pre-con";
        var httpRequest = $http({
            method: 'GET',
            url: 'http://localhost:8080/aws/volumeReport/cleanVolumes',
            params: {accessKey:$scope.accountreport.accessKey, secreetKey:$scope.accountreport.secreetKey}
        }).success(function(data, status) {
            $scope.loading="se-pre-remove";
            alert("All Unused Volumes are removed from all region")
        }).error(function(data, status) {
            $scope.loading="se-pre-remove";
            alert(data.message);
        });
    };
}