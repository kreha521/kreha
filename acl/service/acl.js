var acljson = require('./acl.json');
var settings = {};

for (var i in acljson) {
    for (var url in acljson[i]) {
        settings[url] = acljson[i][url];
    }
}

module.exports = {
    isAuthorized:function(url, user) {
        if (!settings.hasOwnProperty(url)) {
            console.log('Url is not found:' + url);
            return false;
        }

        var roles = settings[url];

        if (roles.indexOf(user) <= -1) {
            console.log('Role is not found:' + url);
            return false;
        }

        console.log("Allowed:" + url); 
        return true;
    }
}
