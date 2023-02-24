const path = require('path');

module.exports = {
    entry: './src/main/webapp/resources/index.js',
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname, './src/main/webapp/resources')
    },
    mode: 'development'
};