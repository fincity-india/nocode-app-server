const path = require("path");

const HtmlWebpackPlugin = require("html-webpack-plugin");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");

const public_path = path.resolve(__dirname, "public");
const src_path = path.resolve(__dirname, "src");

module.exports = {
  entry: path.resolve(src_path, "index.js"),
  output: {
    path: public_path,
    filename: "main.js",
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        include: src_path,
        loader: "babel-loader",
      },
    ],
  },
  devtool: "eval-source-map",
  devServer: {
    static: {
      directory: public_path,
      watch: true,
    },
    compress: true,
    port: 3000,
    historyApiFallback: { index: "/", disableDotRule: true },
    proxy: {
      "**/api/**": {
        target: "http://localhost:8080",
        changeOrigin: true,
        secure: false,
      },
    },
  },
  plugins: [
    new CleanWebpackPlugin({
      cleanOnceBeforeBuildPatterns: [path.resolve(public_path, "index.html")],
    }),
    new HtmlWebpackPlugin({
      hash: true,
      template: path.resolve(src_path, "index.html"),
      filename: path.resolve(public_path, "index.html"),
      base: "/",
    }),
  ],
};
