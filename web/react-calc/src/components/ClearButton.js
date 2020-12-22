import React, { Component } from 'react';
import './ClearButton.css';

class ClearButton extends Component {
    render() {
        return (
		<div className="clearButton"
                onClick={() => this.props.handleClear()}>
		Clear
		</div>
        );
    }
}
export default ClearButton;
