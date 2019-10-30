angular.module('meuapp')
    .controller('FileSelectorController', function($http,$scope) {
        $scope.list = [];
        $scope.parent = '';
        $scope.diretorio = {};
        $scope.listDirectory = function(directory){
            $scope.list = [];

            if(directory){
                $http.get('/api/file/directory/'+directory).then(function (response) {
                    var resp = response.data;
                    if(resp.sucesso){
                        $scope.diretorio = resp.resposta;
                        console.log($scope.diretorio);
                        if($scope.diretorio){
                            $scope.list = $scope.diretorio.arquivos;
                        }else{
                            $scope.list = [];
                        }
                        $scope.list.unshift({'path': $scope.diretorio.parentPath, 'nome': '..', 'dir': true});
                    }
                });
            }else{
                $http.get('/api/file/').then(function (response) {
                    var resp = response.data;
                    console.log(response);
                    if(resp.sucesso){

                        $scope.diretorio = resp.resposta;
                        console.log($scope.diretorio);

                        if($scope.diretorio.arquivos){
                            $scope.list = $scope.diretorio.arquivos;
                        }else{
                            $scope.list = [];
                        }

                        $scope.list.unshift({'path': $scope.diretorio.parentPath, 'nome': '..' , 'dir': true});

                    }
                });
            }

        };
        $scope.selectFile = function(item){
            if(item && item.dir){
                $scope.listDirectory(item.path);
            }
        };

        $scope.listDirectory(null);


        $scope.decodeBase64 = function(s) {
            if(s){
                var e={},i,b=0,c,x,l=0,a,r='',w=String.fromCharCode,L=s.length;
                var A="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
                for(i=0;i<64;i++){e[A.charAt(i)]=i;}
                for(x=0;x<L;x++){
                    c=e[s.charAt(x)];b=(b<<6)+c;l+=6;
                    while(l>=8){((a=(b>>>(l-=8))&0xff)||(x<(L-2)))&&(r+=w(a));}
                }
                return r;
            }

        };


        $scope.formatDate = function(time){
            return new moment(time,'x').format('DD/MM/YYYY');
        }

    });
