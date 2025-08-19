export function isValidName(field: string) {
    var re = /^\w+$/;
  if (!re.test(field)) {
      return false;
  }
  return true;
}