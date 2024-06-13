import java.io.File


var linhasDoMapa :Int =0
var colunasDoMapa : Int =0
var coordnadasValidas:Boolean= false

//valida, conta , processa e tem?

fun validaLinhasEColunas():Boolean{
    var linhasV:Boolean= false
    println("Quantas linhas?")
    var numLinhas: Int? = readLine()?.toIntOrNull()

while (linhasV== false){
    if (numLinhas!=null && numLinhas>0){
        linhasV= true
    }
        if(numLinhas==null ||numLinhas<0){
            println("Resposta invalida")
            println("Quantas linhas?")
            numLinhas = readLine()?.toIntOrNull()
        }

}

    var colunasV:Boolean= false
    println("Quantas colunas?")
    var numColunas: Int? = readLine()?.toIntOrNull()

    while (colunasV== false){
        if (numColunas!=null &&numColunas>0){
           colunasV= true
        }
        if( numColunas==null ||numColunas<0){
            println("Resposta invalida")
            println("Quantas colunas?")
            numColunas = readLine()?.toIntOrNull()
        }

    }
    linhasDoMapa=numLinhas!!
    colunasDoMapa=numColunas!!
    return validaTamanhoMapa(numLinhas,numColunas)
}
fun validaIdade():Boolean{
    val validade = false
    while (!validade) {
        print("Qual a sua data de nascimento? (dd-mm-yyyy)\n")
        val resposta = readLine()
        val test = validaDataNascimento(resposta)
        if(test =="Data invalida"){
            println(test)
        }
        if (test =="Menor de idade nao pode jogar"){
            println(test)
            return false
        }else{
            if(test==null){
                return true

            }
        }
    }
    return validade
}
fun validaTamanhoMapa(numLinhas: Int, numColunas: Int): Boolean{
    if(numLinhas == 6 &&numColunas==5){
                return true
            }
    if((numLinhas == 6 && numColunas==6)){
        return true
    }
    if(numLinhas == 8 && numColunas==8){
        return true
    }
    if( numLinhas == 10 && numColunas==10) {
            return true
        }
    if( numLinhas == 8 && numColunas==10) {
        return true
    }
    if( numLinhas == 10 && numColunas==8) {
        return true
    }

    return false
}
fun validaCoordenadas(terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>, contadoresHorizontais: Array<Int?>):Int{
    var valida = 0


    while(valida == 0){
        println("Coordenadas da tenda? (ex: 1,B)")
        val coordenadas:String? = readLine()
        if (coordenadas=="sair"){
            return 1
        }
        val pairDeCoordenadas=processaCoordenadas(coordenadas,linhasDoMapa,colunasDoMapa)
      if (pairDeCoordenadas==null){

          println("Tenda nao pode ser colocada nestas coordenadas")
      }else{



          if (colocaTenda(terreno,pairDeCoordenadas)){
              valida = 3
              if (terminouJogo(terreno, contadoresVerticais, contadoresHorizontais)) {
                  print("Parabens! Terminou o jogo!\n")
                  valida = 2
              }
          }else{
              println("Tenda nao pode ser colocada nestas coordenadas")


             }

      }

    }

return valida

}
fun validaDataNascimento(data: String?) : String?{

    var menorIdade: Boolean = false
    var validaDia: Int = 0
    if(data == null){
        return "Data invalida"
    }
    if(data.length!=10){
        return "Data invalida"
    }
    if( data[2] != '-' || data[5] != '-' ){
        return "Data invalida"
    }

    val anoConcat = data[6].toString() + data[7].toString()+ data[8].toString()+ data[9].toString()

    if(anoConcat.toInt()<=1900||anoConcat.toInt()>2022){
        return "Data invalida"
    }
    val mesConcatenado =  data[3].toString() + data[4].toString()

    val mes = mesConcatenado.toInt()
    if(mes>12||mes<1){
        return "Data invalida"
    }
    if (mes == 1 || mes == 3 || mes == 5 || mes == 7 ||
        mes == 8 || mes == 10 || mes == 12) {
        validaDia=31
    } else{ if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
        validaDia=30
    }else{
        if ((anoConcat.toInt()% 4 == 0 && anoConcat.toInt() % 100 != 0) || anoConcat.toInt() % 400 == 0) {
            validaDia=29
        } else {
            validaDia=28
        }
    }
    }
    val diaConcatenado =  data[0].toString() + data[1].toString()
    if(anoConcat.toInt()<2004){
        if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 ||
                    mes == 8 || mes == 10 || mes == 12)&& diaConcatenado.toInt()>31) {
            return "Data invalida"
        }  else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11)&& diaConcatenado.toInt()>30) {
            return "Data invalida"
        }else{
            if (((anoConcat.toInt()% 4 == 0 && anoConcat.toInt() % 100 != 0) ||
                        anoConcat.toInt() % 400 == 0)&& diaConcatenado.toInt()>29) {
                return "Data invalida"
            } else { if (mes == 2 &&diaConcatenado.toInt()>28) {
                return "Data invalida"
            }
            }
        }
    }
    if(anoConcat.toInt()==2004){
        if (mes==11||mes==12){
            if (diaConcatenado.toInt()>=1){
                menorIdade= true
            }
        }
    }
    if(anoConcat.toInt()>=2005){
        menorIdade=true
    }
    if (menorIdade ==true ) {
        return "Menor de idade nao pode jogar"
    }else {
        return null
    }
}
fun processaCoordenadas(coordenadasStr: String?, numLines: Int, numColumns: Int): Pair<Int,Int>? {
    if (coordenadasStr == null) {
        return null }
    if(coordenadasStr.length>4|| coordenadasStr.length<3){
        return null }

    val asciicode=65

    if(coordenadasStr.length==3){
        if(coordenadasStr[1] != ','){
            return null
        }
        if(!coordenadasStr[0].isDigit()){
            return null
        }
        if(coordenadasStr[0].digitToInt()<1||coordenadasStr[0].digitToInt()>numLines){
            return null
        }
        if (!coordenadasStr[2].isLetter()||!coordenadasStr[2].isUpperCase()){
            return null
        }
        if (coordenadasStr[2].code <65 ||coordenadasStr[2].code >64+numColumns){
            return null
        }
    }

    if(coordenadasStr.length==4){
        if(coordenadasStr[2] != ','){

            return null
        }
        if(!coordenadasStr[0].isDigit()||!coordenadasStr[1].isDigit()){

            return null
        }
        val digitum= coordenadasStr[0].toString()
        val digitdois= coordenadasStr[1].toString()
        val strlinhas :String = digitum+digitdois
        if(strlinhas.toInt()<1||strlinhas.toInt()>numLines){

            return null
        }
        if (!coordenadasStr[3].isLetter()||!coordenadasStr[3].isUpperCase()){

            return null
        }
        if (coordenadasStr[3].code <65 ||coordenadasStr[3].code >64+numColumns){

            return null
        }
    }
    var aLetra:Int=0
    var num:String=""
    if(coordenadasStr.length==4){
        aLetra=1
        num = coordenadasStr[0].toString()+coordenadasStr[1].toString()
    }
    if(aLetra==1) {

        return Pair(num.toInt() - 1,coordenadasStr[3].code-asciicode)
    }else{

        return Pair(coordenadasStr[0].digitToInt() - 1,coordenadasStr[2].code-asciicode)
    }

}
fun temArvoreAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>) : Boolean{

    val copiaArray: Array<Array<String?>> = Array(12){Array(12){null} }
    val coordsCopiaArray = Pair(coords.first+1,coords.second+1)

    for (i in terreno.indices) {
        for (j in 0 until terreno[i].size) {
            copiaArray[i+1][j+1]= terreno[i][j]
        }
    }
    if(copiaArray[coordsCopiaArray.first][coordsCopiaArray.second+1]=="A"){
        return true
    }

   // if(copiaArray[coordsCopiaArray.first-1][coordsCopiaArray.second]=="A"){
     //   return true
    //}

    if(copiaArray[coordsCopiaArray.first][coordsCopiaArray.second-1]=="A"){
        return true
    }

    //if(copiaArray[coordsCopiaArray.first+1][coordsCopiaArray.second]=="A"){
      //  return true
    //}


    return false

}
fun temTendaAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>) : Boolean{
    val copiaArray: Array<Array<String?>> = Array(12){Array(12){null} }
    val coordsCopiaArray = Pair(coords.first+1,coords.second+1)

    for (i in terreno.indices) {
        for (j in 0 until terreno[i].size) {
           copiaArray[i+1][j+1]= terreno[i][j]
        }
    }
    if(copiaArray[coordsCopiaArray.first][coordsCopiaArray.second+1]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first-1][coordsCopiaArray.second+1]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first-1][coordsCopiaArray.second]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first-1][coordsCopiaArray.second-1]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first][coordsCopiaArray.second-1]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first+1][coordsCopiaArray.second-1]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first+1][coordsCopiaArray.second]=="T"){
        return true
    }
    if(copiaArray[coordsCopiaArray.first+1][coordsCopiaArray.second+1]=="T"){
        return true
    }

    return false
}
fun contaTendasColuna(terreno:Array<Array<String?>>, coluna: Int): Int {
    var countTendas: Int = 0
    if (coluna<terreno[0].size&&coluna>=0) {
        for (i in 0 until terreno.size) {
            if (terreno[i][coluna] == "T") {
                countTendas++
            }
        }
        return countTendas
    }else
        return countTendas
}
fun contaTendasLinha(terreno: Array<Array<String?>>, linha: Int): Int {

    var countTendas: Int = 0
    if (linha<terreno.size&&linha>=0) {
        for (i in 0 until terreno[linha].size) {
            if (terreno[linha][i] == "A") {
                countTendas++
            }
        }
        return countTendas
    }else
        return countTendas

}
fun colocaTenda(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean{
if(terreno[coords.first][coords.second]==null){
    if(temArvoreAdjacente(terreno,coords)) {
        if (!temTendaAdjacente(terreno, coords)) {
            if(terreno[coords.first][coords.second]=="T"){
            terreno[coords.first][coords.second] = null
            return true
        }
            terreno[coords.first][coords.second] = "T"
            return true
        }
    }

}
    return false
}
fun terminouJogo(terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>, contadoresHorizontais: Array<Int?>): Boolean{


    val checkNumTendasVerticais =   Array(contadoresVerticais.size){0}
    val checkNumTendasHorizontais = Array(contadoresHorizontais.size){0}
    for (i in  contadoresHorizontais.indices) {
       if(contadoresHorizontais[i]==null){
           contadoresHorizontais[i]=0
       }
    }
    for (i in  contadoresVerticais.indices) {
        if(contadoresVerticais[i]==null){
            contadoresVerticais[i]=0

        }
    }
    var countArvores=0
    var countTendas=0


    for (i in terreno.indices) {
        for (j in 0 until terreno[i].size) {
            if ((terreno[i][j]=="T")) {
                checkNumTendasVerticais[j] += 1
                checkNumTendasHorizontais[i] += 1
                countTendas++
            }
                if ((terreno[i][j]=="A")) {
                    countArvores++
                }

        }
    }

val isEqualV = checkNumTendasVerticais contentEquals contadoresVerticais
    val isEqualH = checkNumTendasHorizontais contentEquals contadoresHorizontais

    if(isEqualV&& isEqualH &&countArvores==countTendas){
        return true
    }
    return false
}

//ler ficheiros

fun leTerrenoDoFicheiro(numLines: Int, numColumns: Int): Array<Array<String?>>{
    val ficheiro = File("${numLines}x${numColumns}.txt").readLines()
    val outputArray: Array<Array<String?>> = Array(numLines){Array<String?>(numColumns){null} }
    for (i in 2 until ficheiro.size){
        val arvores : Array<String> = ficheiro[i].split(",").toTypedArray()
        outputArray[arvores[0].toInt()][arvores[1].toInt()]= "A"

    }
return outputArray
}
fun leContadoresDoFicheiro(numLines: Int, numColumns: Int, verticais: Boolean): Array<Int?>{
    val contadores = File("${numLines}x${numColumns}.txt").readLines()
    val outputArray: Array<String>
    if(!verticais){
           outputArray = contadores[1].split(",").toTypedArray()
   }else{
       outputArray = contadores[0].split(",").toTypedArray()
   }
    val output:Array<Int?> = arrayOfNulls(outputArray.size)
    for(i in 0 until outputArray.size){
        if(outputArray[i].toInt()==0){
            output[i]=null

        }else {
            output[i] = outputArray[i].toInt()
        }
    }

return  output
}

//criação da interface

fun jogar():Boolean{
    var num : Int? = readLine()?.toIntOrNull()
    if (num==0){
        return false
    }
    while(num!=1){
        println("Opcao invalida")
        println(criaMenu())
        num = readLine()?.toIntOrNull()
        if (num==0){
            return false
        }
        if(num==1){
            return true
        }

    }
    return true
}
fun criaMenu(): String{


    return "\nBem vindo ao jogo das tendas\n\n1 - Novo jogo\n0 - Sair\n"

}
fun criaLegendaVerticalContadores(contadoresHorizontais: Array<Int?>?,terreno: Array<Array<String?>>,legenda: Boolean):String{
   var output:String=""
    var outputSemContadores:String = ""


        if (contadoresHorizontais!=null&&legenda) {
            var count: Int = 0
            for (i in terreno.indices) {
                for (j in -2 until terreno[i].size) {

                    if(j==-2) {

                        if (contadoresHorizontais[count] != null) {
                            output += "${contadoresHorizontais[count]} "
                        } else {
                            output += "  "
                        }
                        count++
                    }else {

                        if (j == -1) {
                            if (i >= 9) {
                                output += "${i + 1} "
                            } else {
                                output += " ${i + 1} "
                            }
                        } else {
                            if (j != terreno[i].size-1) {
                                if(terreno[i][j]=="A"){
                                    output+="| △ "

                                }else{if(terreno[i][j]=="T"){

                                    output+="| T "
                                }else{if(terreno[i][j]==null){
                                    output += "|   "
                                }
                                }

                                }
                            } else {
                                if(terreno[i][j]=="A"){
                                    output+="| △"
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                }else{if(terreno[i][j]=="T"){

                                    output+="| T"
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                }else{if(terreno[i][j]==null){
                                    output += "|  "
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                }
                                }

                                }

                            }

                        }


                    }
                }
            }
            return output

        }else {
            if (legenda&&contadoresHorizontais==null){
                for (i in terreno.indices) {
                    for (j in -1 until terreno[i].size) {

                        if (j == -1) {
                            if (i >= 9) {
                                output += "  ${i + 1} "
                            } else {
                                output += "   ${i + 1} "
                            }
                        } else {
                            if (j != terreno[i].size-1) {
                                if(terreno[i][j]=="A"){
                                    output+="| △ "

                                }else{if(terreno[i][j]=="T"){

                                    output+="| T "
                                }else{if(terreno[i][j]==null){
                                    output += "|   "
                                }
                                }

                                }
                            } else {

                                if(terreno[i][j]=="A"){
                                    output+="| △"
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                }else{if(terreno[i][j]=="T"){

                                    output+="| T"
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                }else{if(terreno[i][j]==null){
                                    output += "|  "
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                }
                                }

                                }

                            }

                        }


                    }
                }
               return output
            }else {
                if (!legenda && contadoresHorizontais==null){
                    for (i in terreno.indices) {
                        output += "     "
                        for (j in 0 until terreno[i].size) {

                            if (j != terreno[i].size - 1) {
                                if (terreno[i][j] == "A") {
                                    output += "| △ "

                                } else {
                                    if (terreno[i][j] == "T") {

                                        output += "| T "
                                    } else {
                                        if (terreno[i][j] == null) {
                                            output += "|   "
                                        }
                                    }

                                }
                            } else {
                                if (terreno[i][j] == "A") {
                                    output += "| △"
                                    if (i!=terreno.size-1){
                                        output+="\n"
                                    }
                                } else {
                                    if (terreno[i][j] == "T") {

                                        output += "| T"
                                        if (i!=terreno.size-1){
                                            output+="\n"
                                        }
                                    } else {
                                        if (terreno[i][j] == null) {
                                            output += "|  "
                                            if (i!=terreno.size-1){
                                                output+="\n"
                                            }
                                        }
                                    }

                                }

                            }

                        }


                    }
                    return output
                }else{
                    var count: Int = 0
                    for (i in terreno.indices) {
                        for (j in -2 until terreno[i].size) {

                            if(j==-2) {

                                if (contadoresHorizontais?.get(count) != null) {
                                    output += "${contadoresHorizontais[count]} "
                                } else {
                                    output += "  "
                                }
                                count++
                            }else {

                                if (j == -1) {
                                    if (i >= 9) {
                                        output += "   "
                                    } else {
                                        output += "   "
                                    }
                                } else {
                                    if (j != terreno[i].size-1) {
                                        if(terreno[i][j]=="A"){
                                            output+="| △ "

                                        }else{if(terreno[i][j]=="T"){

                                            output+="| T "
                                        }else{if(terreno[i][j]==null){
                                            output += "|   "
                                        }
                                        }

                                        }
                                    } else {
                                        if(terreno[i][j]=="A"){
                                            output+="| △"
                                            if (i!=terreno.size-1){
                                                output+="\n"
                                            }
                                        }else{if(terreno[i][j]=="T"){

                                            output+="| T"
                                            if (i!=terreno.size-1){
                                                output+="\n"
                                            }
                                        }else{if(terreno[i][j]==null){
                                            output += "|  "
                                            if (i!=terreno.size-1){
                                                output+="\n"
                                            }

                                        }
                                        }

                                        }

                                    }

                                }


                            }
                        }
                    }
                    return    output


                }
            }


        }


    return output
}
fun criaTerreno(terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>?, contadoresHorizontais: Array<Int?>?, mostraLegendaHorizontal: Boolean=true, mostraLegendaVertical: Boolean=true): String{
    var output =""



        if (contadoresVerticais!=null){
            output+= "       "+criaLegendaContadoresHorizontal(contadoresVerticais)+"\n"


        }

    if(mostraLegendaHorizontal){
        output+="     | "+criaLegendaHorizontal(terreno[1].size)+"\n"
    }else{
        if (contadoresVerticais != null) {

                println()
        }
    }

        output+=criaLegendaVerticalContadores(contadoresHorizontais,terreno,mostraLegendaVertical)
        output +="\n"
    for(i in terreno[1].indices){
        if (i==0){
            output+="  "
            output+="-------"
        }else{
            if (i!=terreno[1].size-1){
                output+="----"
            }else{
                output+="---"
            }
        }

    }

return output
}

fun criaLegendaContadoresHorizontal(contadoresHorizontal: Array<Int?>?): String{
    var output : String=""


        if (contadoresHorizontal!=null) {
            for(i in contadoresHorizontal.indices){
                if(i != contadoresHorizontal.size-1) {
                    if (contadoresHorizontal[i]==null){
                        output +=  "    "
                    }else {
                        output += contadoresHorizontal[i].toString() + "   "
                    }
                }else{

                    output+= contadoresHorizontal[i].toString()
                }
            }
        }

    return output
}
fun criaLegendaHorizontal(numColunas: Int): String{
   var asciiValue=65
    var count = 0
    var output : String =""
    while(count !=numColunas){

       val transformacao = asciiValue.toChar()
            output+="$transformacao"
        count++
       asciiValue++
        if (count !=numColunas){
            output+=" | "
        }


    }

 return output
}



fun secondMain() {

    var terreno: Array<Array<String?>>
    var validaLoopInicio = false
    var loopcoordenadas:Int=3
    while (!validaLoopInicio) {
        println(criaMenu())

        if (jogar()) {

            validaLoopInicio = validaLinhasEColunas()//
            if (!validaLoopInicio) {//
                println("Terreno invalido")//
            } else {//
               // validaLinhasEColunas()
                if (linhasDoMapa == 10 && colunasDoMapa == 10){
                if (validaIdade()) {
                    terreno = leTerrenoDoFicheiro(linhasDoMapa, colunasDoMapa)
                    while (loopcoordenadas == 3) {
                        val contadoresV = leContadoresDoFicheiro(linhasDoMapa, colunasDoMapa, true)
                        val contadoresH = leContadoresDoFicheiro(linhasDoMapa, colunasDoMapa, false)
                        println()
                        println(criaTerreno(terreno, contadoresV, contadoresH))
                        val verifica = validaCoordenadas(terreno, contadoresV, contadoresH)
                        if (verifica == 1) {
                            loopcoordenadas = 1
                            validaLoopInicio = true

                        } else {
                            if (verifica == 2) {
                                loopcoordenadas = 2
                                validaLoopInicio=false
                            }
                        }

                    }

                } else {
                    validaLoopInicio = true


                }


            }else{
                    terreno = leTerrenoDoFicheiro(linhasDoMapa, colunasDoMapa)

                    while (loopcoordenadas == 3) {
                        val contadoresV = leContadoresDoFicheiro(linhasDoMapa, colunasDoMapa, true)
                        val contadoresH = leContadoresDoFicheiro(linhasDoMapa, colunasDoMapa, false)
                        println()
                        println(criaTerreno(terreno, contadoresV, contadoresH))
                        val verifica = validaCoordenadas(terreno, contadoresV, contadoresH)
                        if (verifica == 1) {
                            loopcoordenadas = 1
                            validaLoopInicio = true

                        } else {
                            if (verifica == 2) {
                                loopcoordenadas = 2
                                validaLoopInicio=false
                            }
                        }

                    }
            }

        }//22
    }else{
        validaLoopInicio=true
    }


        }
    }

fun main() {
    secondMain()
}