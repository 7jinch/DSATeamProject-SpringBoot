import modal from '../components/modal.js';
import changeTitle from '../utils/changeTitle.js';

const memberLogin = () => {
  changeTitle('로그인');

  const html = `
    <p class="login-title">로그인</p>
    <div class="input-box">
      <div>
        <p>이메일</P>
        <input type="text" id="userId" placeholder="이메일을 입력해 주세요" />
      </div>
      <div>
        <p>비밀번호</p>
        <input type="password" id="userPassword" placeholder="비밀번호를 입력해 주세요" />
      </div>
      <br>
      <div class="login-button-box">
        <input type="button" class="main-page-button" value="메인으로" />
        <input type="button" class="signup-page-button" value="회원가입하기" />
        <input type="button" class="login-page-button" value="로그인하기" />
      </div>
    </div>
    ${modal()}
  `;

  return html;
};

export default memberLogin;
