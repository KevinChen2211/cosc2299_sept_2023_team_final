import React, { Component } from "react";
import "../../switchButton.css"

export default class SwitchButton extends Component {
    constructor() {
        super();
        this.state = {
            selected: "btn1"
        };
    }

    changeColor = (btn) => {
        this.setState({ selected: btn });
    };

    render() {
        return (
            <div className="option">
                <div className="buttons">
                    <button
                        id="option1Btn"
                        className={
                            this.state.selected === "btn1" ? "selected" : "notSelected"
                        }
                        onClick={() => this.changeColor("btn1")}
                    >
                        {" "}
                        9AM - 1PM{" "}
                    </button>
                    <button
                        className={
                            this.state.selected === "btn2" ? "selected" : "notSelected"
                        }
                        onClick={() => this.changeColor("btn2")}
                    >
                        {" "}
                        1PM - 5PM{" "}
                    </button>
                    <button
                        className={
                            this.state.selected === "btn3" ? "selected" : "notSelected"
                        }
                        onClick={() => this.changeColor("btn3")}
                    >
                        {" "}
                        5PM - 9PM{" "}
                    </button>
                </div>
            </div>
        );
    }
}
