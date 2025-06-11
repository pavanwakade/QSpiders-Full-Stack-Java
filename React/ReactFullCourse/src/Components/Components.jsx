import { Component } from 'react';
import ComponentsIntro from './ComponentsIntro/ComponentsIntro';
import Jxsintro from './JsxIntro/JsxIntro';

class Components extends Component {
  render() {
    return (
      <div>
        <ComponentsIntro />
        <Jxsintro></Jxsintro>
      </div>
    );
  }
}

export default Components;
