angular.module('meuapp')
    .controller('ListVersaoController', function($http,$scope) {
        $scope.versoes = [];
        $http.get('/api/versao').then(function (response) {
            console.log('foi',response);
            $scope.versoes = response.data;
        });

        $scope.formatDate = function(time){
            return new moment(time,'x').format('DD/MM/YYYY');
        }

    });
