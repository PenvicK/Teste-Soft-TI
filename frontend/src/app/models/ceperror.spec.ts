import { CEPError } from './ceperror';

describe('CEPError', () => {
  it('should create an instance', () => {
    expect(new CEPError()).toBeTruthy();
  });
});
