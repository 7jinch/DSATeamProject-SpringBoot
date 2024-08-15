/**
 * url 바꾸는 함수
 */
const changeURL = (url) => {
  history.pushState(null, null, url);
};

export default changeURL;
