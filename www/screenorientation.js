var exec = require('cordova/exec');

module.exports = {
    orientationType: [
        'any',
        'landscape',
        'portrait',
        'portrait-primary',
        'portrait-secondary',
        'landscape-primary',
        'landscape-secondary'
    ],
    setOrientation: function (type, success) {
        exec(success, null, 'ScreenOrientation', 'orientation', [type]);
    }
}
