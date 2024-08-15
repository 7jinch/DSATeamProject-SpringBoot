/**
 * 헤더 컴포넌트와 푸터 컴포넌트를 페이지에 합쳐서 문자열로 반환하는 함수
 */
import header from '../components/header.js';
import footer from '../components/footer.js';
import bindHeaderButtonEvent from './bindHeaderButtonEvent.js';

const concatHeaderFooterPage = (page) => {
  const headerString = header();
  const footerString = footer();
  const html = `
    ${headerString}
    <div id="page">
      ${page}
    </div>
    ${footerString}
  `; // 헤더, 푸터, 페이지 문자열 합치기
  const app = document.querySelector('#app');
  app.innerHTML = html; // 페이지 생성하기

  bindHeaderButtonEvent(); // 헤더의 버튼에 이벤트 바인딩하기
};

export default concatHeaderFooterPage;
