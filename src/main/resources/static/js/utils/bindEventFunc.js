const bindEvnetFunc = (...args) => {
  args.forEach((func) => {
    func();
  });
};

export default bindEvnetFunc;
