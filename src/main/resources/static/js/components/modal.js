/**
 * 모달 컴포넌트
 */
const modal = () => `
  <div id="modal-box" class="hidden">
    <div id="modal-content">
      <p id="modal-title">실패!</p>
      <p id="modal-msg">실패 메시지</p>
      <input type="button" id="modal-close" value="X" />
    </div>
  </div>
`;

export default modal;
