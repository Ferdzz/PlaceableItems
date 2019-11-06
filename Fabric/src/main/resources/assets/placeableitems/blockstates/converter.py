import json
import sys
import os
import itertools

def main():
    for item in os.listdir(os.curdir):
        print(item)
        if not item.endswith(".json"):
            continue
        file = open(item,"r")
        beefBlock  = json.loads(file.read())
        variants = beefBlock["variants"]
        variantions = {}
        permutations = {}
        finalStates = []
        converted = {}
        if("multiblock" not in beefBlock and "forge_marker" in beefBlock and item.endswith(".json")):
            for key in variants:
                variantions[key] = []
                for attrib in variants[key]:
                    variantions[key].append(attrib)
            #print(variantions)
            for key in variantions:
                permutations[key] = []
                for attrib in variants[key]:
                    permutations[key].append(key + "=" + attrib)
                    #print(key + " " + attrib)
            #print(permutations)
            permArray = []
            index = 0
            for key in permutations:
                valueList = []
                for value in permutations[key]:
                    valueList.append(value)
                permArray.append(valueList)
                index+=1

            #print(permArray)
            finalStates = list(itertools.product(*permArray))
            for state in finalStates:
                stateConcat = ""
                for attrib in state:
                    stateConcat += attrib + ","
                stateConcat = stateConcat[0:-1]
                converted[stateConcat] = []
                for key in stateConcat.split(','):
                    if("defaults" in beefBlock):
                        converted[stateConcat].append(beefBlock["defaults"])
                    converted[stateConcat].append(variants[key.split('=')[0]][key.split('=')[1]])
            #file = open("converted.json",'w+',encoding='utf-8')
            unknown = {}
            unknown["variants"] = converted

            raw_convert = json.dumps( unknown)
            raw_convert = raw_convert.replace("}}}],","}}}],\n")
            raw_convert = raw_convert.replace("{\"transform\": {", "")
            raw_convert = raw_convert.replace("}}}],","}},")
            raw_convert = raw_convert.replace("[","{")
            raw_convert = raw_convert.replace("]","")
            raw_convert = raw_convert.replace("{{","{")
            raw_convert = raw_convert.replace("\"}, \"", "\", \"")
            if "default" in beefBlock:
                raw_convert = raw_convert.replace("}}}}}","}}}}")
            #raw_convert = raw_convert.replace(" \"rotation\": {\"", " \"")
            #raw_convert = raw_convert.replace("}},","},")
            file = open(item,"w+")
            file.write(raw_convert[0:-1])
            file.close()
        else:
            print("skipped")

            
            



main()