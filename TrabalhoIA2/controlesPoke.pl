%Primeiros Controles do Jogo Pokémon
%Trabalho de IA
%Referências: https://pt.wikibooks.org/wiki/Prolog/Matem%C3%A1tica

%Conhecimentos
 %Para movimentar.
 :-dynamic coordenadas/2.
 :-dynamic sentido/1.
 %Tipos de terreno.
 terreno(0).
 terreno(grama).
 terreno(1).
 terreno(agua).
 terreno(2).
 terreno(montanha).
 terreno(3).
 terreno(caverna).
 terreno(4).
 terreno(vulcao).
 %A respeito dos Pokémons.
 % pokemon(NOME, CODIGO, TIPO).
 :-dynamic pokemon/3.
 %A respeito do MAPA.
 %mapa(COORDENADA_X, COORDENADA_Y, TIPO_TERRENO).
 :-dynamic mapa/3.
 %Para ter conhecimento das coordenadas que já passou.
 %mapaExplorado(COORDENADA_X, COORDENADA_Y).
 :-dynamic mapaExplorado/2.
 %Para as pokebolas.
 :-dynamic pokebolas/1.

%Regras
%Para caminhar sobre terreno.
 passar_por(X):-terreno(X), pokemon(_,_,X),!.
 possiveis_caminhos(X,Y):-pokemon(_,_,Z), mapa(X,Y,Z).
 %Para passar por terrenos de acorodo com o pokemon.
 possiveis_caminhos_proximos(K,W):-coordenadas(X,Y), pokemon(_,_,Z), ((K is X, W is Y+1);(K is X, W is Y-1);(K is X+1, W is Y);(K is X-1, W is Y)), mapa(K,W,Z).
% Para passar por terreno de grama sem pokemons ou os de grama.
 possiveis_caminhos_proximos(K,W):-coordenadas(X,Y),((K is X, W is Y+1);(K is X, W is Y-1);(K is X+1, W is Y);(K is X-1, W is Y)), mapa(K,W,grama).
% Para verificar os caminhos segundo o sentido.
 segue_sentido_direto(K,W,3):-coordenadas(X,Y),(K is X-1, W is Y).
 segue_sentido_direto(K,W,2):-coordenadas(X,Y),(K is X, W is Y+1).
 segue_sentido_direto(K,W,1):-coordenadas(X,Y),(K is X+1, W is Y).
 segue_sentido_direto(K,W,0):-coordenadas(X,Y),(K is X, W is Y-1).
 segue_sentido(K,W):-sentido(X), segue_sentido_direto(K,W,X), possiveis_caminhos_proximos(K,W).

 operacao(K,W):-segue_sentido(K,W),armazenaCoordenada(K,W).
 operacao(_,_):-coordenadas(X,Y),sentido(SO),possiveis_caminhos_proximos(K,W),sentidoDesejado(X,Y,K,W,SD,SO),verificarGiro(SO,SD).

 quanto_falta(X,Y,Z,W):-coordenadas(A,B), Z is X-A, W is Y-B.

 planejar(X,Y):-coordenadas(X,Y).

%Para capturar pokemons.
 capturar(X,Y,Z):-pokebolas(W),(W>0),asserta(pokemon(X,Y,Z)), (K is W-1), setarPokebolas(K).

%Para recarregar pokebolas.
 recarregarPokebolas:-pokebolas(X), Z is X +25, setarPokebolas(Z).

%Condições iniciais.
 setarCoordenadas(X,Y):-limites(X,Y), asserta(coordenadas(X,Y)).
 setarSentido(X):-X>=0, X<4, asserta(sentido(X)).
 setarPokebolas(X):-asserta(pokebolas(X)).
 inicializar:-setarCoordenadas(2,1),setarSentido(2), setarPokebolas(25).
%Para mudar as coordenadas.
 armazenaExplorado:-coordenadas(X,Y), asserta(mapaExplorado(X,Y)).
 limpaCoordenadas:-retractall(coordenadas(_,_)).
 armazenaCoordenada(X,Y):-armazenaExplorado, limpaCoordenadas, setarCoordenadas(X,Y).
 limites(X,Y):-X<42,X>=0,Y<42,Y>=0.
%Para girar.
 limpaSentido:-retractall(sentido(_)).
 sentidoEmCicloEsq(X):-X<4->asserta(sentido(X)); asserta(sentido(0)).
 sentidoEmCicloDir(X):-X>=0->asserta(sentido(X)); asserta(sentido(3)).
 giraEsquerda:-sentido(Y), Z is 1 + Y ,limpaSentido, sentidoEmCicloEsq(Z).
 giraDireita:-sentido(Y), Z is -1 + Y, limpaSentido, sentidoEmCicloDir(Z).

 verificarGiro(SO,SD):-(SO=\=SD),(SO>SD->(giraDireita);((SO<SD)->(giraEsquerda))).%Precisa ser recursivo para girar quantas vezes for necessário.
 sentidoDesejado(_,Y,_,W,SD,SO):-(Y=\=W),((Y>W)->SD is 0;((Y<W)-> SD is 2;SD is SO)).
 sentidoDesejado(X,_,K,_,SD,SO):-(X=\=K),((X>K)->SD is 3;((X<K)-> SD is 1;SD is SO)).











