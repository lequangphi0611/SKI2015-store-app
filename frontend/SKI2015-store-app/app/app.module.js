(() => {

    'use trict';

    angular

        .module('app', [])

        .component('appStore', {
            templateUrl: "../app/app.template.html",
            controller: appController
        });

    angular.element(function() {
        angular.bootstrap(document, ['app']);
    });
}) ();

function appController() {

}

