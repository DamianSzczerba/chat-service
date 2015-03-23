var app = angular.module('app', ['ui.router', 'ngSanitize']);

app.service('Username', function() {
    var loggedIn = "";

    var setUsername = function(user) {
        loggedIn = user;
    }

    var getUser = function() {
        return loggedIn;
    }

    return {
        setUsername: setUsername,
        getUser: getUser
    };
});

app.service('Receiver', function() {
    var receiver = "";

    var setReceiver = function(user) {
        receiver = user;
    }

    var getReceiver = function() {
        return receiver;
    }

    return {
        setReceiver: setReceiver,
        getReceiver: getReceiver
    };
});

app.config(function($stateProvider, $urlRouterProvider){

    // For any unmatched url, send to /login
    $urlRouterProvider.otherwise("/login")
    $stateProvider

    // Login page
    .state('login', {
        url: "/login",
        templateUrl: "app/partials/login.html"
    })

    // Chat window
    .state('chat', {
        url: "/chat",
        templateUrl: "app/partials/chat.html"
    })

    // Private chat window
    .state('userChat', {
        url: "/userChat",
        parent: "chat",
        templateUrl: "app/partials/userChat.html"
    })

})

app.controller('LoginController', ['$scope', '$http', 'Username',
    function($scope, $http, Username) {

        // Send the username to Dropwizard then open the chat page
        $scope.authenticate = function(username) {
            if(username != "" && username != undefined) {
                $http.post('http://localhost:8080/users', {
                    "username": username
                }).success();
                $scope.set = Username.setUsername(username);
                $scope.apply();
            }
        }
}]);


app.controller('ChatController', ['$scope', '$http', 'Username', '$state', 'Receiver',
    function($scope, $http, Username, $state, Receiver) {

        // Get the username of the person who is logged in
        $scope.getUsername = Username.getUser();

        // Get the list of users who are online
        $scope.users = [];
        $http.get('http://localhost:8080/users').success(function(data) {
                return $scope.users = data;
        });

        // Assign the person the user is talking to a variable
        $scope.assignReceiver = function(talkingWith) {
            $scope.setReceiver = Receiver.setReceiver(talkingWith);
        }

        // Get the person the user is talking to
        $scope.getReceiver = Receiver.getReceiver();

        // Send the message
        $scope.sendMessage = function(message) {
            var chat = getChatLogs();
            var sender = $scope.getUsername;
            var receiver = $scope.getReceiver;
            var key = sender + "-" + receiver;

            if(chat == undefined || chat == "") {
                // Update chat logs between the sender and receiver
                updateChatLogs(sender, receiver, message);

                // Update the chat window by combining old and updated chat logs
                var old = document.getElementById('oldLogs').innerHTML;
                var time = getTime();
                var content = old + "<br>" + time + " <span class='loggedInAs'> " + sender + "</span>: " + message;
                $scope.conversationWindow = content;
                $scope.message = "";

            } else { // else is never used
                // Get the old chat logs
                var oldLogs = chat.get(key);

                // Update chat logs between the sender and receiver
                updateChatLogs(sender, receiver, message);

                // Get updated chat logs
                var newLogs = chat.get(key);

                // Update the chat window by combining old and updated chat logs
                $scope.conversationWindow = oldLogs + "<br>" + newLogs;

            }
        }

        var getTime = function() {
            var date = new Date();

            var hours = date.getHours();
            if(hours.toString().length == 1) {
                var hoursString = hours.toString();
                var hours = "0" + hoursString;
            }

            var minutes = date.getMinutes();
            if(minutes.toString().length == 1) {
                var minutesString = minutes.toString();
                var minutes = "0" + minutesString;
            }

            var seconds = date.getSeconds();
            if(seconds.toString().length == 1) {
                var secondsString = seconds.toString();
                var seconds = "0" + secondsString;
            }

            var time = hours + ":" + minutes + ":" + seconds;
            return time;
        }

        var getChatLogs = function() {
            $http.get('http://localhost:8080/userChat').success(function(data) {
                return $scope.chatLogs = data;

            });
            //return $scope.chatLogs;
        }

        var updateChatLogs = function(sender, receiver, message) {
            $http.post('http://localhost:8080/userChat', {
                "sender": sender,
                "receiver": receiver,
                "message": message
            }).success();
        }
}]);


/*
app.factory('httpInterceptor', function httpInterceptor ($q, $window, $location) {
    return {
        request: function (config) {
            config.headers = config.headers || {};
            if ($window.sessionStorage.token) {
                config.headers.Authorization = 'Bearer ' + $window.sessionStorage.token;
            }
            return config;
        },
        response: function (response) {
            return response || $q.when(response);
        }
    }
});
*/