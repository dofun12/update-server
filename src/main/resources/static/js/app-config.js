console.log('Starting new module');
var meuapp = angular.module('meuapp',
    [ 'ui.router','ui.bootstrap']);
console.log('Starting configs');
meuapp
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/nova");
        //
        // Now set up the states
        $stateProvider
            .state('nova', {
                url: "/nova",
                controller: 'NovaVersaoController',
                templateUrl: '/js/pages/nova-versao/nova-versao.html'
            })
            .state('editar', {
                url: "/editar/:id",
                controller: 'NovaVersaoController',
                templateUrl: '/js/pages/nova-versao/nova-versao.html'
            })
            .state('list', {
                url: "/list",
                controller: 'ListVersaoController',
                templateUrl: '/js/pages/list-versao/list-versao.html'
            })

        ;
    });
console.log('Starting app-controller');
meuapp.controller('AppController', function($scope,$transitions) {
    $scope.currentState = 'none';
    $transitions.onStart({}, function(trans) {
        trans.promise.then(function (value) {
            $scope.currentState = value.name;
        });
    });
    $scope.isActive = function (link) {
        console.log('Comparando....',link,$scope.currentState, link === $scope.currentState);
        return link === $scope.currentState;
    }
});
