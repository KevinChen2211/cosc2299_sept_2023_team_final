import React from "react";
import { useNavigate } from "react-router";
import BackArrow from "../../assets/back.png"

export default function BackButton() {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    return <>
        <button onClick={goBack} className="default-home-button">
            <img
                src={BackArrow}
                width="40rem"
            />
            <div>Back</div>
        </button>
        <br />
        <br />
    </>;
}