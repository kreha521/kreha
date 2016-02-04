#!/bin/bash

declare -A execCheck

isNumeric(){
	local -r value="$1"
	if [[ "${value}" =~ ^(0|[1-9]+[0-9]*)$ ]]; then
		return
	fi
	echo "ERROR: The argument is not numeric."
}

isRange(){
	local -r value="$1"
	local -r min="$2"
	local -r max="$3"
	if [ ${value} -lt ${min} -o ${value} -gt ${max} ]; then
		echo "ERROR: The argument is out of range."
	fi
}

isDate(){
	local -r value="$1"
	if [ "$(date +'%Y%m%d' -d "${value}" 2> /dev/null)" != "${value}" ]; then
		echo "ERROR: The argument is invalid date."
	fi
}

printStdIn(){
	local -r menuList="$1"
	local -r inputItem="$2"

	cat <<- EOF
		${menuList}
		
	EOF
	echo -n "${inputItem}"
}

printErrOut(){
	local -r msg="$1"

	cat <<- EOF
		
		$msg
		
	EOF
}

selected=""
inputStdIn(){
	local -r menuList="$1"
	local -r inputItem="$2"
	local -r min="$3"
	local -r max="$4"

	printStdIn "${menuList}" "${inputItem}"

	while read value; do
		if [ ${execCheck["isRange"]} -eq 1 ]; then
			ret=$(isNumeric "${value}")
			if [ ! -z "${ret}" ]; then
				printErrOut "${ret}"
				printStdIn "${menuList}" "${inputItem}"
				continue
			fi

			ret=$(isRange "${value}" "${min}" "${max}")
			if [ ! -z "${ret}" ]; then
				printErrOut "${ret}"
				printStdIn "${menuList}" "${inputItem}"
				continue
			fi
		fi

		if [ ${execCheck["isDate"]} -eq 1 ]; then
			ret=$(isDate "${value}")
			if [ ! -z "${ret}" ]; then
				printErrOut "${ret}"
				printStdIn "${menuList}" "${inputItem}"
				continue
			fi
		fi

		selected="${value}"
		break
	done
}

printMenuList1(){
	cat <<- EOF
		1:Menu1
		2:Menu2
		3:Menu3
	EOF
}

##Input 1
execCheck["isRange"]=1
execCheck["isDate"]=0
inputStdIn "$(printMenuList1)" "Input->" 1 3

echo "${selected}"

exit 0

