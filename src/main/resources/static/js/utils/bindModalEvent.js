export const bindModalActiveEvent = () =>
  (document.querySelector('#modal-box').style.display = 'flex');

export const bindModalCloseEvent = () => {
  document.querySelector('#modal-close').addEventListener('click', () => {
    document.querySelector('#modal-box').style.display = 'none';
  });
};
