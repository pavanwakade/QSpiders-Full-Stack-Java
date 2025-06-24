import { useState } from 'react';
import PropsTas2Child from './PropsTas2Child';

const PropsTas2 = () => {
    const [state, setState] = useState(0); 
    return (
        <>
            <div>PropsTas2</div>
            <PropsTas2Child props={{ state, setState }} />
        </>
    )
}

export default PropsTas2