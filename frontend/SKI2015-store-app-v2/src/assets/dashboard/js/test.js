var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/1", {
            templateUrl: "product.html?" + Math.random()
        })
        .when("/2", {
            templateUrl: "category.html?" + Math.random()
        })
        .when("/3", {
            templateUrl: "home.html?" + Math.random()
        })
        .when("/4", {
            templateUrl: "gio.html?" + Math.random()
        })
        .when("/5", {
            templateUrl: "dangky.html?" + Math.random()
        })
        .when("/", {
            templateUrl: "product.html?" + Math.random()
        });
});