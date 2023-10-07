import React from "react";
import { useNavigate } from "react-router";

export default function BackButton() {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    return <>
        <button onClick={goBack} className="default-home-button">
            Back
        </button>
        <br />
        <br />
    </>;
}