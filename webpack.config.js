// for dev, from Wharu home, run 
//		webpack --config ./src/com/wheru/config/webpack.config.js
// for prod, run 
// 		NODE_ENV=production webpack --config ./src/com/wheru/config/webpack.config.js

var debug = process.env.NODE_ENV !== "production";
var webpack = require('webpack');
var path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'src/main/webapp/resources/js');
var APP_DIR = path.resolve(__dirname, 'src/main/webapp/resources/js');

module.exports = {
	devtool: debug ? "inline-sourcemap" : false,
	entry: APP_DIR + "/scripts.jsx",
	output: {
		path: BUILD_DIR,
		filename: "scripts.min.js"
	},
	plugins: debug ? [] : [ 
		new webpack.optimize.OccurrenceOrderPlugin(),
		new webpack.optimize.UglifyJsPlugin({ mangle : false, sourcemap : false }), 
	],
	resolve: {
		extensions: ['.js', '.jsx'],
		modules: ["node_modules"]
	},
	module: {
	    loaders: [
	        {
	        	test: /\.jsx?$/,
	        	loader: 'babel-loader',
	        	exclude:  /(node_modules|bower_components)/,
	        	query: {
	        		presets: ['es2015', 'react']
	        	}
	        },
	        {
	           test: /\.css$/,
	           use: ['style-loader', 'css-loader']
	         }
	     ]
	},
	node: {
		net: 'empty',
		dns: 'empty'
	}
};
  
