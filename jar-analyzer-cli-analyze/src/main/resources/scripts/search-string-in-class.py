# usage: --script search-string-in-class --input [class-name] --output result.txt
# example: --input java/lang/String
import sys
from me.n1ar4.analyze import db_instance
from me.n1ar4.analyze import method_util

print("[python] script-input:  " + str(input))
print("[python] script-output: " + str(output))

param = str(input)
param = param.replace(".", "/")

db = db_instance()
sql = "SELECT value,method_name,method_desc,class_name,jar_name FROM string_table WHERE class_name = '__input__'"
sql = sql.replace("__input__", param)
result = db.execute_sql(sql)

print("[python] script write output")
file = open(str(output), "w")
for item in list(result):
    i = dict(item)
    for k, v in i.items():
        if str(k) == 'method_desc':
            value = method_util.desc_to_str(v)
            file.write(str(k) + ":" + value + "   ")
            file.write("method_desc_native:" + str(v) + "   ")
        else:
            file.write(str(k) + ":" + str(v) + "   ")
    file.write("\n")
file.close()
print("[python] script run finish")