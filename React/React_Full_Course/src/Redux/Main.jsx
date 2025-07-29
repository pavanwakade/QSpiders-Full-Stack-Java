import { Provider } from 'react-redux'
import { store } from './store.js'
import ReduxApp from './ReduxApp.jsx'

const Main = () => {
    return (
        <Provider store={store} >
            <ReduxApp />
        </Provider>
    )
}

export default Main