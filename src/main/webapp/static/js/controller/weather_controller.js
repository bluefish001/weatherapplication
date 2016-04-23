App.controller('WeatherController', ['$scope', 'WeatherService', function($scope, WeatherService) {
          var self = this;
          $scope.cities=['Sydney','Melbourne','Wollongong']
          self.weather={city:null,updatedTime:'',weather:'',temperature:'',wind:''};
               
          self.fetchWeather = function(){
        	  WeatherService.fetchWeather($scope.city)
                  .then(
                               function(d) {
                                    self.weathers = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Currencies');
                                }
                       );
          };
                                      
      }]);