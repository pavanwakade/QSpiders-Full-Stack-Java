
// const PropsTasks1Child = (x) => {
//     console.log(x);

import PropsTasks1Child1Child from "./PropsTasks1Child1Child";

const PropsTasks1Child = (props) => {
    // console.log(props);
    // console.log(props.props.names);
    return (
        <>
        <div>PropsTasks1Child</div>
        <PropsTasks1Child1Child props={props.props}/>
        </>
    )
}

export default PropsTasks1Child