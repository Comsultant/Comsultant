const PriceFormatter = (value) => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ",") + `원`;
export default PriceFormatter;