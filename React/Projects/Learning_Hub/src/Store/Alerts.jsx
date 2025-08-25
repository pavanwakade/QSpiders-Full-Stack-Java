import React from 'react'

export const Alerts = (type, message) => {
    let back;
    if (type === "Error") {
        back = "red"
    }
    else {
        back = "green"
    }
    return (
        <div style={{ background: { back } }}>
            {message}
        </div>
    )
}

