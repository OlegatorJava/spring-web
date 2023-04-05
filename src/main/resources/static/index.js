angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/api/v1/products';

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/api/v1/auth', $scope.user)
            .then(function successCallback(responce) {
                if(responce.data.token){
                    $http.default.headers.common.Authorization = 'Bearer ' + responce.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: responce.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(responce){

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };
    $scope.clearUser = function (){
        delete $localStorage.springWebUser;
        $http.default.headers.common.Authorization = '';
    };
    $scope.isUserLoggedIn = function () {
        if($localStorage.springWebUser){
            return true;
        }else{
            return false;
        }
    };

    $scope.loadProducts = function () {
        $http({
            url: "http://localhost:8189/app/api/v1/products",
            method: 'get',
            params: {
                p: $scope.p,
                min_cost: $scope.min,
                max_cost: $scope.max,
                name: $scope.name
            }
        }).then(function (response) {
                $scope.ProductsPage = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.removeProductFromCart = function (productDtoId) {
        $http.get("http://localhost:8189/app/api/v1/cart/remove/" + productDtoId)
            .then(function (response) {
                $scope.loadProductsInCart();
            });
    };


    $scope.addProductInCart = function (productDtoId) {
        console.log(productDtoId)
        $http.get("http://localhost:8189/app/api/v1/cart/add/" +  productDtoId)
            .then(function (response) {
                $scope.loadProductsInCart();
            });
    };
    $scope.loadProductsInCart = function () {

        $http.get("http://localhost:8189/app/api/v1/cart")
            .then(function (response) {
            $scope.cart = response.data;
            console.log(response)
        });
    };

    $scope.loadProducts();
})