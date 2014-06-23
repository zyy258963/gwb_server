function isFloat(oNum) {
	if (!oNum)
		return false;
	var strP = /^\d+(\.\d+)?$/;
	if (!strP.test(oNum))
		return false;
	try {
		if (parseFloat(oNum) != oNum)
			return false;
	} catch (ex) {
		return false;
	}
	return true;
}

/* 验证正整数 */
function isNumber(oNum) {
	if (!oNum)
		return false;
	var strP = /^\d+$/; // 正整数
	if (!strP.test(oNum))
		return false;
	return true;
}

/* 验证邮箱 */
function isEmail(oEmail) {
	if (!oEmail)
		return false;
	var strP =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/; // 邮箱
	if (!strP.test(oEmail))
		return false;
	return true;
}

