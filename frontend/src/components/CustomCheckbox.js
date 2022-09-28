import React from "react";
import { Checkbox } from "antd";
import styled from "styled-components";

const NewCheckbox = styled(Checkbox)`
  ${(props) =>
    props.backgroundColor &&
    `
    & .ant-checkbox-checked .ant-checkbox-inner {
      background-color: ${props.backgroundColor};
      border-color: ${props.backgroundColor};
    }
  `}
`;

const CustomCheckbox = ({backgroundColor, state, setter}) => {
  return(
    <NewCheckbox backgroundColor={backgroundColor} checked={state} onClick={()=>setter(!state)} />
  );
}

export default CustomCheckbox;