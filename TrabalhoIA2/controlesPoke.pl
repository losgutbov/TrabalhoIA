%Primeiros Controles do Jogo Pok�mon
%Trabalho de IA
%Refer�ncias: https://pt.wikibooks.org/wiki/Prolog/Matem%C3%A1tica

%Conhecimentos
 %Para movimentar.
 :-dynamic coordenadas/2.
 :-dynamic sentido/1.
 %Fatos correspondentes � sensores para perceber o ambiente
  estimulo(gritoTreinador). %gritos do treinador
  estimulo(perfumeJoy). %perfume de joy
  estimulo(ouvirVendedor). %agente ouve o vendedor oferencendo pokemons

 %Tipos de terreno.
 terreno(grama).
 terreno(agua).
 terreno(montanha).
 terreno(caverna).
 terreno(vulcao).
 %Tipos que d�o acesso a outros terrenos
 acesso(voo).
 acesso(agua).
 acesso(eletrico).
 acesso(fogo).

 %A respeito dos Pok�mons.
 % pokemon(NOME, CODIGO, TIPO1, TIPO2, TIPO3, TIPO_TERRENO).
 :-dynamic pokemon/6.
 %A respeito do MAPA.
 %mapa(COORDENADA_X, COORDENADA_Y, TIPO_TERRENO).
 :-dynamic mapa/3.
 %Para ter conhecimento das coordenadas que j� passou.
 %mapaExplorado(COORDENADA_X, COORDENADA_Y).
 :-dynamic mapaExplorado/2.
 %Para as pokebolas.
 :-dynamic pokebolas/1.
 %Para saber a energia dos Pokemons
 :-dynamic energia/1.
 %Para saber a quantidade de Pokemons capturados.
 :-dynamic totalPokemons/1.
 %Para a pontua��o total.
 :-dynamic pontos/1.

%Regras
 %Para armazenar pokemons classificando por terreno.
 classificaPokeTerreno(NOME,COD,agua,T2,T3,_):-acesso(agua), asserta(pokemon(NOME,COD,agua,T2,T3,agua)).
 classificaPokeTerreno(NOME,COD,fogo,T2,T3,_):-acesso(fogo), asserta(pokemon(NOME,COD,fogo,T2,T3,vulcao)).
 classificaPokeTerreno(NOME,COD,eletrico,T2,T3,_):-acesso(eletrico), asserta(pokemon(NOME,COD,eletrico,T2,T3,caverna)).
 classificaPokeTerreno(NOME,COD,voo,T2,T3,_):-acesso(voo), asserta(pokemon(NOME,COD,voo,T2,T3,montanha)).
 armazenaPoke(NOME,COD,T1,T2,T3,_):-assertz(pokemon(NOME,COD,T1,T2,T3,-)).
 incrementarPokemons:-totalPokemons(T), NOVOTOTAL is T +1, setarTotalPokemons(NOVOTOTAL).
 setarPokemon(NOME,COD,T1,T2,T3,_):-((classificaPokeTerreno(NOME,COD,T1,T2,T3,_),classificaPokeTerreno(NOME,COD,T2,T1,T3,_),classificaPokeTerreno(NOME,COD,T3,T2,T1,_));armazenaPoke(NOME,COD,T1,T2,T3,-)), incrementarPokemons.

 %Para passar as informa��es para o java.
 passarInformacoes(CoordenadaX, CoordenadaY, Pontos, Pokebolas, Carga, TotalPokemons):-coordenadas(CoordenadaX, CoordenadaY), pontos(Pontos), pokebolas(Pokebolas), energia(Carga), totalPokemons(TotalPokemons).

%Para caminhar sobre terreno.
 passar_por(X):-terreno(X), pokemon(_,_,_,_,_,X),!.
 possiveis_caminhos(X,Y):-pokemon(_,_,_,_,_,Z), mapa(X,Y,Z).
 %Para passar por terrenos de acorodo com o pokemon.
 possiveis_caminhos_proximos(K,W):-coordenadas(X,Y), pokemon(_,_,_,_,_,Z), ((K is X, W is Y+1);(K is X, W is Y-1);(K is X+1, W is Y);(K is X-1, W is Y)), mapa(K,W,Z).
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


/*
estimuloAdjacentes(gritoTreinador):-
estimuloAdjacentes(perfumeJoy):-
estimuloAdjacentes(ouvirVendedor):-
*/



%Para recarregar pokebolas.
 recarregarPokebolas:-pokebolas(X), Z is X +25, setarPokebolas(Z).

%Para recarregar energia dos Pokemons.
 recarregarEnergia:-energia(Carga), (Carga=\=1), setarEnergia(1).

%Para batalhar
 batalha(GouP):-totalPokemons(Qtd), (Qtd>0), ((energia(Carga), Carga=:=1)->(GouP is 1);(GouP is 0)).
 batalha(_):-totalPokemons(Qtd), (Qtd=:=0).

%Condi��es iniciais.
 armazenarTerrenos(X,Y,Z):-limites(X,Y),asserta(mapa(X,Y,Z)).
 setarCoordenadas(X,Y):-limites(X,Y), asserta(coordenadas(X,Y)).
 setarSentido(X):-X>=0, X<4, asserta(sentido(X)).
 setarPokebolas(X):-asserta(pokebolas(X)).
 setarEnergia(Carga):-asserta(energia(Carga)).
 setarTotalPokemons(Quantidade):-asserta(totalPokemons(Quantidade)).
 setarPontos(Pontos):-asserta(pontos(Pontos)).
 inicializar:-setarCoordenadas(24,19),setarSentido(2), setarPokebolas(25), setarEnergia(1), setarTotalPokemons(0), setarPontos(0).
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

 verificarGiro(SO,SD):-(SO=\=SD),(SO>SD->(giraDireita);((SO<SD)->(giraEsquerda))).%Precisa ser recursivo para girar quantas vezes for necess�rio.
 sentidoDesejado(_,Y,_,W,SD,SO):-(Y=\=W),((Y>W)->SD is 0;((Y<W)-> SD is 2;SD is SO)).
 sentidoDesejado(X,_,K,_,SD,SO):-(X=\=K),((X>K)->SD is 3;((X<K)-> SD is 1;SD is SO)).











