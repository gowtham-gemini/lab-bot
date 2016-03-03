var app = angular.module('myApp', []);
function ReportCtrl($scope, $http, $location) {
    $scope.accounts = [];
    $scope.regions = [];
    $scope.baseURL = window.location.origin;
    $scope.unUsedVolumes = [];
    $scope.loading = "";
    $scope.accountreport = {};
    $scope.loadAcountReport = function() {
    	$scope.loading="se-pre-con";
    	console.log($scope);
        var httpRequest = $http({
            method: 'GET',
            url: $scope.baseURL+'/aws/usage/accountReport',
            params: {account: $scope.accountreport.account_name, accessKey:$scope.accountreport.accessKey, secretKey:$scope.accountreport.secretKey}

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
            url: $scope.baseURL+'/aws/usage/region/flavorReport',
            params: {accessKey:$scope.accountreport.accessKey, secretKey:$scope.accountreport.secretKey}

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
            url: $scope.baseURL+'/aws/volumeReport/unusedVolumes',
            params: {accessKey:$scope.accountreport.accessKey, secretKey:$scope.accountreport.secretKey}

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
            url: $scope.baseURL+'/aws/volumeReport/cleanVolumes',
            params: {accessKey:$scope.accountreport.accessKey, secretKey:$scope.accountreport.secretKey}
        }).success(function(data, status) {
            $scope.loading="se-pre-remove";
            alert("All Unused Volumes are removed from all region")
        }).error(function(data, status) {
            $scope.loading="se-pre-remove";
            alert(data.message);
        });
    };
}