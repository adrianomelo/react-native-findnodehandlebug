import React, { Component } from 'react';
import ReactNative, {
  AppRegistry,
  NativeAppEventEmitter,
  View,
  WebView,
  NativeModules
} from 'react-native';
const { JSContext } = NativeModules;

export default class rn extends Component {
  _onNavigationStateChange(e) {
    const reactTag = ReactNative.findNodeHandle(this.refs['webView1']);

    JSContext.findWebView(e.target)
      .then(() => {
        console.log('e.target WebView found');
      })
      .catch((e) => {
        console.log('e.target WebView not found');
      });

    JSContext.findWebView(reactTag)
      .then(() => {
        console.log('findNodeHandle WebView found');
      })
      .catch((e) => {
        console.log('findNodeHandle WebView not found');
      });
  }

  render() {
    return (
      <WebView ref='webView1'
        source={{uri: 'http://duckduckgo.com/'}}
        onNavigationStateChange={this._onNavigationStateChange.bind(this)}
      />
    );
  }
}

AppRegistry.registerComponent('rn', () => rn);
