'use strict';

var React = require('react-native');
var { requireNativeComponent, PropTypes, View } = React;

var NativeAndroidSegmented = requireNativeComponent('AndroidSegmented', AndroidSegmented);

class AndroidSegmented extends React.Component {
  constructor() {
    super();
    this._onChange = this._onChange.bind(this);
  }

  _onChange(event) {
    if (this.props.onChange) {
      this.props.onChange(event.nativeEvent);
    }
  }

  render() {
    return (
      <NativeAndroidSegmented
        {...this.props} 
        onChange={this._onChange}/>
    );
  }
}

var colorType = function (props, propName, componentName) {
  var checker = function() {
    var color = props[propName];
    var regex = /^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$/;
    if (!regex.test(color)) {
      return new Error('Only accept color formats: #RRGGBB and #AARRGGBB');
    }
  };

  return PropTypes.string(props, propName, componentName) || checker();
}

AndroidSegmented.propTypes = {
  ...View.propTypes,
  childText: PropTypes.arrayOf(PropTypes.oneOfType([ PropTypes.string ])),
  orientation:PropTypes.string,
  tintColor:PropTypes.arrayOf(PropTypes.oneOfType([ PropTypes.string ])),
  selectedPosition:PropTypes.number,
  onChange: PropTypes.func,
}

AndroidSegmented.defaultProps = {
  
};

module.exports = AndroidSegmented;