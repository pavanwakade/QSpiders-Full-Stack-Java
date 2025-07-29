import React, { createContext, useContext, useState } from 'react';

const FormContext = createContext();

function FormProvider({ children }) {
  const [data, setData] = useState({ name: '', address: '' });
  return (
    <FormContext.Provider value={{ data, setData }}>
      {children}
    </FormContext.Provider>
  );
}

function Step1({ next }) {
  const { data, setData } = useContext(FormContext);
  return (
    <div>
      <input
        value={data.name}
        onChange={e => setData(d => ({ ...d, name: e.target.value }))}
        placeholder="Name"
      />
      <button onClick={next}>Next</button>
    </div>
  );
}
function Step2({ next, prev }) {
  const { data, setData } = useContext(FormContext);
  return (
    <div>
      <input
        value={data.address}
        onChange={e => setData(d => ({ ...d, address: e.target.value }))}
        placeholder="Address"
      />
      <button onClick={prev}>Back</button>
      <button onClick={next}>Next</button>
    </div>
  );
}
function Step3({ prev }) {
  const { data } = useContext(FormContext);
  return (
    <div>
      <div>Name: {data.name}</div>
      <div>Address: {data.address}</div>
      <button onClick={prev}>Back</button>
    </div>
  );
}
function MultiStepFormWithSharedState() {
  const [step, setStep] = useState(1);
  return (
    <FormProvider>
      {step === 1 && <Step1 next={() => setStep(2)} />}
      {step === 2 && <Step2 next={() => setStep(3)} prev={() => setStep(1)} />}
      {step === 3 && <Step3 prev={() => setStep(2)} />}
    </FormProvider>
  );
}
export default MultiStepFormWithSharedState;
