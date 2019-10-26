angular.module('meuapp')
    .controller('NovaVersaoController', function($scope,$http,$stateParams) {
        var prefix = '/api/versao';

        var isNew = false;
        var id = $stateParams.id;
        if(id){
            $http.get(prefix+'/'+$stateParams.id).then(function (response) {
                $scope.model = response.data;
                $scope.model.dataReleaseObj = new moment($scope.model.dataRelease,'x').toDate();
                $scope.header = 'Editar versão';
            });
        }else{
            isNew = true;
            $scope.model = {
                dataReleaseObj: new Date()
            };
            $scope.header = 'Nova versão';
        }

        $scope.popup1 = {
            opened: false
        };
        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };
        $scope.format = 'dd/MM/yyyy';
        $scope.open1 = function() {
            console.log('Foi');
            $scope.popup1.opened = true;
        };

        this.save = function(){
            console.log('Saving',$scope.model);
            var versao = {
              id: id,
              nome: $scope.model.nome,
              dataRelease: new moment($scope.model.dataReleaseObj).format('x'),
              status: $scope.model.status
            };

            if(isNew){
                $http.post(prefix,versao).then(function (response) {
                    $scope.model = response.data.resposta;
                    $scope.model.dataReleaseObj = new moment($scope.model.dataRelease,'x').toDate();
                });
            }else{
                $http.put(prefix+'/'+id,versao).then(function (response) {
                    $scope.model = response.data.resposta;
                    $scope.model.dataReleaseObj = new moment($scope.model.dataRelease,'x').toDate();
                    console.log('Updating..',versao,$scope.model);
                });
            }

        };

    });
/**
angular.element(document).ready(function(){
    var values = angular.module('meuapp')._invokeQueue;
    angular.forEach(values, function(value, key) {
        if(value[0] == '$controllerProvider'){
            console.log(value[2][0]);
        }
    });
});
**/
