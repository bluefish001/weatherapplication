/**
 * 
 */
'use strict';
 
App.factory('WeatherService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchWeather: function(city) {
            		console.log('city is '+city);
                    return $http.get('http://localhost:8080/weatherapplication/getweather?cities='+city)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching users');
                                        return $q.reject(errResponse);
                                    }
                            );
            }       
    };
 
}]);