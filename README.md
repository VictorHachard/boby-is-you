# BobyIsYou

\documentclass[a4paper]{article}
\usepackage{amsmath}
\usepackage{graphicx}
\usepackage{hyperref}
\usepackage{float}
\usepackage[utf8x]{inputenc}
\usepackage[french]{babel}
\usepackage[T1]{fontenc}
\usepackage{xcolor}
\usepackage[a4paper,top=3cm,bottom=2cm,left=3cm,right=3cm,marginparwidth=1.75cm]{geometry}

\renewcommand{\baselinestretch}{1.2}
\setlength{\parindent}{3em}
\setlength{\parskip}{0.5em}

\definecolor{lightgray}{gray}{0.96}
\newcommand{\code}[1]{\colorbox{lightgray}{\texttt{#1}}}
\newcommand{\entry}[2]{\textbf{#1}\markboth{#1}{#1}\ { #2}}

\title{Projet d'informatique BAB1 : Boby Is You}
\author{Victor Hachard}
\date{30 avril 2018}

\begin{document}

\centerline{\includegraphics[width=1\textwidth]{logo.png}}
{\let\newpage\relax\maketitle}
\maketitle
\pagebreak

\tableofcontents
\pagebreak

\section{Présentation}
Dans le cadre de la première année de bachelier en Sciences Informatiques, un projet de programmation doit être réalisé. Ce projet est un jeu vidéo qui doit être inspiré par BabaIsYou\cite{BabaIsYou}. L'objectif est de réaliser ce jeu vidéo en se basant sur les cours enseignés et d'y appliquer des notion fondamentale telle que l'héritage et la programmions orientée objet [POO].

\section{Méthodologie}
La premier étape a été de créé un UML du projet en fessent abstraction de l'interface graphique. Ensuite cela a était de développer le coeur du jeu : la modélisation d'un niveau et le système de règle qui sont expliquer par la suite dans la section \hyperref[dec]{Décision}. Pour implémenter cela, le cours de Programmation et Algorithmique II de M. Quoitin\cite{Algo2} sur la POO a était utiliser.  Enfin l'ajout de la partie graphique qui communique à chaque déplacement de l'utilisateur avec la parie modèle également expliquer dans la partie section \hyperref[dec]{Décision}. La référence pour l'implémentation de l'interface graphique ...

\section{Décisions}\label{dec}
\subsection{Décision sur les packages}
Application du design paterne modèle, vus qui sépare de la partie graphique de la modélisation.
Dans la partie modèle, il y a la logique du jeu qui communique directement avec la partie graphique. De plus il y a le package ressources qui comprend des sous-packages : CSS, images, musiques, niveaux, police.

\subsection{Décision sur la modélisation d'un niveau}
La modélisation d'un niveau est constituée d'un tableau 2D de taille variable, une matrice. Cette matrice est constituée d'une liste de taille variable. Cette liste est constituée d'éléments. Chaque élément est défini par un \code{TypeElement}, des règles et la direction de déplacement du \code{TypeElement}.
\code{TypeElement} est définie au point \hyperref[te]{Décision sur la structure des \code{TypeElement}}. 

Le tableau 2D de taille variable permet d’être très flexible avec de nouvelles règles, des games modes, ce qui n'aurait pas été possible avec une liste de taille fixe.

L'avantage est une implémentation facile, une très grande liberté et un code très modulaire.

\begin{figure}[H]
\centering
\includegraphics[width=0.8\textwidth]{1.png}
\caption{\label{fig:hierarchy}Illustration de la modélisation d'un niveau}
\end{figure}

\subsection{Décision sur la structure des TypeElement}
Un système d'héritage pour la structure des \code{TypeElement} n'a pas été implémenté. Cette solution en revanche est bien plus modulable et permet la surcharge de méthode.

\begin{figure}[H]
\centering
\includegraphics[width=0.7\textwidth]{4.png}
\caption{\label{fig:hierarchy}Illustration de la modélisation des \code{TypeElement} (héritage)} \label{te}
\end{figure}
Une énumération a était préféré dans ce cas pour éviter le surplus de classe et un code plus concis. L'énumération d'un \code{TypeElement} est défini par un ordre de priorité à l'affichage, un nom, un \code{Type}. \code{Type} est aussi une énumération qui permet de faire la distinction entre les diffèrents \code{TypeElement} par exemple un Type Block, Type Rule, Type Text. Si le \code{TypeElement} est un Text il aura en plus un \code{TypeElement} et si c'est une Rule il aura une \code{Property}.
\code{Property} est définie au point \hyperref[regle]{Décision sur la modélisation des règles}.

\begin{figure}[H]
\centering
\includegraphics[width=0.5\textwidth]{3.png}
\caption{\label{fig:hierarchy}Illustration de la modélisation des \code{TypeElement} (énumération)}
\end{figure}

\bigskip
\noindent\begin{minipage}{\textwidth}
\noindent Voici ci-dessou l'énumération \code{TypeElement}, pour des raisons de clarté, tout n'est pas implémenté.
\begin{verbatim}
public enum TypeElement {
    
    ROCK(2,null,null,Type.BLOCK),
    LAVA(1,null,null,Type.BLOCK),
    PLAYER1(3,null,null,Type.PLAYER),
    TEXT_FLAG(3,TypeElement.FLAG,null,Type.TEXT),
    TEXT_EMPTY(3,TypeElement.EMPTY,null,Type.TEXT),
    PUSH(3,null,Property.PUSH,Type.RULE),
    STOP(3,null,Property.STOP,Type.RULE),
    YOU(3,null,Property.YOU,Type.RULE),
    KILL(3,null,Property.KILL,Type.RULE),
    IS(3,null,null,Type.CONNECTER);
    
    static TypeElement fromString(String element) {...}
}
\end{verbatim}
\end{minipage}

\subsection{Décision sur la modélisation des règles} \label{regle}
La modélisation des règles est basée sur un modèle d’héritage : toutes les règles ont une classe parent \code{Rule} qui est constituée d'une liste chaînée statique. Cette liste chaînée est parcourue à chaque déplacement du joueur. Chaque élément de la liste chaînée est activable ou désactivable, cela évite de parcourir inutilement des règles. Chaque règle contient la méthode \code{check} qui vérifie si la règle doit s'appliquer et être exécutée. Toutes les règles sont définies par une énumération \code{Property} pour un soucie d'organisation.

\begin{figure}[H]
\centering
\includegraphics[width=0.7\textwidth]{2.png}
\caption{\label{fig:hierarchy}Illustration de la modélisation des règles}
\end{figure}

\bigskip
\noindent\begin{minipage}{\textwidth}
\noindent Voici ci-dessou la classe parent \code{Rule}, pour des raisons de clarté, tout n'est pas implémenté.\label{regleEx}
\begin{verbatim}
public abstract class Rule {

    private static Hashmap<Property,Boolean> ...;
    
    static void setActivity(Property, Boolean) {...}
    
    public boolean check(Position, Directions, TypeElement) throws WinException {
        boolean res = true;
        if (isActive(this.getProperty()))
            res = work(pos,dir,te);
        if (nextRule == null)
            return res;
        return res && nextRule.check(pos,dir,te);
    }
    abstract boolean work(Position, Directions, TypeElement) throws WinException;
    abstract Property getProperty();
        
}
\end{verbatim}

\bigskip
\bigskip
\noindent Voici ci-dessou un exemple de la classe \code{Win}, pour des raisons de clarté, tout n'est pas implémenté.
\begin{verbatim}
public class Win extends Rule {

    @Override
    public boolean work(Position, Directions, TypeElement) throws WinException {
        if "En fonction de la direction, trouve dans la case suivante Win."
            throw new WinException();
        return true;
    }
    @Override
    Property getProperty() {...}

}
\end{verbatim}
\end{minipage}

\subsection{Décision sur la structure des fichiers}
\noindent Les fichiers suivant sont crée à la racine du projet.

\begin{itemize} 
 	\item \code{achievement} sauvegarde de la progression des succès, une sauvegarde automatique a lieu tout les 50 déplacements.
 	\item \code{crash.log} sauvegarde des erreurs produites par le jeu.
	\item \code{config.txt} sauvegarde des préférences et de l'avancement de l'utilisateur, une sauvegarde a lieu après une modification dans le menu \code{Paramètre} et lors d'un passage de niveau.
\end{itemize} 

\section{Points forts}
\subsection{Optimisations}
\noindent Voicie les optimisations les plus importantes en terme de consommation en mémoire ou processeur sont citées ci-dessous.

\subsubsection{Optimisation générale}
\begin{itemize} 
   \item Une liste \code{allElement} de taille variable se trouve en attribut de \code{Board}. Cette liste comprend tous les éléments trouvables dans le niveau chargé, la liste 2D qui contient le niveau est complétée avec des éléments définis dans \code{allElement}. Cela permet une réduction de charge en mémoire et cela évite de parcourir la liste 2D pour trouver ou changer un élément.
   \item Le calcul des positions des \code{IS} n'est exécuté qu'une fois à la construction du niveau, puis si le joueur déplace un \code{IS} la position est éditée. Cela évite encore de parcourir une liste 2D.
   \item Tous les niveaux, images, musiques utilisés ne sont chargés qu'une seule fois et gardés en mémoire. Pour éviter d'avoir des lags et chargements.
\end{itemize}

\subsubsection{Optimisation du système de règles}
\begin{itemize}
   \item Le calcul des règles est refait à chaque fois que le joueur déplace quelque chose. Cela permet d'éviter de recalculer les règles si aucune modification n'est apportée sur le plateau. Une solution plus avancée serait d'implémenter le design pattern observeur/observé.
   \item La vérification et l'exécution des règles. Comme cité au point \hyperref[regle]{Décision sur la modélisation des règles} et avec l'exemple \hyperref[regleEx]{des classes \code{Rule} et \code{Win}}. A chaque déplacement du joueur la méthode \code{check} dans \code{Rule} est exécutée. \code{Rule} comme cité au point \hyperref[regle]{Décision sur la modélisation des règles} est constituée d'une liste chaînée qui garde en mémoire toutes les règles du niveau, seules les règles actives sont vérifiées, si une des règles vérifiée s'exécute, les autre ne seront pas vérifiées.
   \item Lors d'un déplacent du joueur, la vérification des règles de la case suivante et effectuée. Comme dans chaque \code{Case} il y a une liste d'éléments. La vérification s'exécute sur l'ensemble des éléments. Mais si un des éléments possèdes les règles en question, nul besoin de continuer pour voir si les autres l'ont aussi. Cela permet un gain de temps important car ces vérifications se produisent très régulièrement.
\end{itemize}

\subsection{Fonctionnalité supplémentaire}
\begin{itemize} 
   \item Deux Games modes : déplacement limité et course contre le temps sont optionnels.
   \item Un mode coopératif, le premier niveau le démontre comme exemple.
   \item Nouvelles règles : slip, fly, move, melt, hot, kill, tp, sink, strong et weak.
   \item Nouveaux blocs : love, lava, ice, skull.
   \item Nouveaux niveaux.
   \item Un nouveau connecteur : \code{AND}.\\
    par exemple : \code{WALL AND ROCK IS MOVE AND KILL}
   \item Succès : A chaque fois que l'on se déplace, gagne, recommence, un succès est débloqué. De plus un menu regroupe toutes les statistiques du joueur.
   \item Musiques : une musique de fond, des bruitages lors des passages de niveau, des déblocages de succès ...
\end{itemize}

\subsection{Modification et POO}
L'implémentation du projet est entièrement en POO. Ce qui facilite l'ajout de règles, de blocs, de game mode.

\section{Points faibles}
\subsection{Erreurs d'optimisations}
Les erreurs citées sont les plus importantes en terme de consommation de mémoire ou de rapidité d'exécution. Toute les solutions proposées n'ont pas été implémentées par manque de temps ou connaissance.

\begin{itemize} 
   \item L'affichage du niveau dans la partie graphique est refaite à chaque déplacement du joueur. Une solution pourrait être envisagée : l'implémentation du design pattern observeur, observé pour ne rafraîchir que les images qui auraient changé d'état.
   \item Le calcul des positions du joueur est refaite à chaque déplacement. Une solution moyenne serait de garder en mémoire la position du joueur et l'éditer comme pour les \code{IS}. Mais le joueur peut changer de \code{TypeElement} ou de nombre : donc il faudrait détecter quand cela arrive et recalculer toutes les positions.
   \item La vérification de la position des règles n'est pas implémenter proprement. La vérification n'est qu'un enchaînement de "if", "else" sur cent lignes.\label{reglepaspropre}
\end{itemize}

\section{Erreurs connue et difficulté rencontré}
\subsection{Erreur connue}
\begin{itemize} 
 	\item Lors d'un arrêt brutale du jeu (alt+f4), les fichiers de sauvegarde ne sont pas mis à jour. pus la
\end{itemize} 
\subsection{Difficulté rencontré}
Les deux vrais difficulté rencontré fut le système de règle plutôt complexe, lors de la vérification de la position des règles. Qui n'est implémenter proprement, cité dans les \hyperlink{reglepaspropre}{Erreur d'optimisation}. Et lors de la création de l'interface graphique en JavaFX\cite{JavaFX} qui est très pauvre en documentation ce qui causa la difficulté.

\section{Conclusion}
\textbf{Apports positifs et/ou négatifs}

...

\newpage
\appendix
\section{Remerciement}
Je tiens à remercier toutes les personnes qui ont contribué au projet et lors de la rédaction de ce rapport.\\

Tout d'abord, pour les nombreux échanges sur la structure et l'implémentions du projet, Pierre Woi, étudiant à HE2B ESI en deuxième en Informatique Industriel\\

Ensuite, pour la correction du rapport, Daniel Hachard, poète.\\

De plus pour l'environnement du jeu, les musiques et bruitage, Clément Roart, étudiant à Umons en BAB3 Mathématique. Les Textures Johan Nevroux, étudiant à l'Human Academy en première ....\\

Pour finir, je remercie pour l'aide général qu'il a pu apporté, Thomas Lavend'Homme, étudiant à Umons en première année en Science Informatique.\\

\section{Vocabulaire}
\label{voc}

\entry{POO}{La programmation orientée objet.}

\entry{UML}{Langage de Modélisation Unifié.}
 
\entry{Connecteur}{Élément qui connecte un bloc à une règle.}

\section{Guide}
\subsection{Contrôles}
\begin{itemize} 
	\item \code{pageup}, \code{pagedown} augmente, diminue le volume général du jeu.
	\item De-bug : \code{P}, \code{M} permettent de passer les niveaux.
    \item De-bug : \code{O} permet de remettre à zéro les Games Modes\footnotemark.
    \footnotetext{Attention les Games Modes peuvent ou non se réactiver.}
    \item De-bug : \code{L} permet de remettre à zéro les règles\footnotemark.
    \footnotetext{Attention les règles se réactiveront au prochain déplacement ou changement de niveau.}
\end{itemize} 

\noindent Par défaut : pour se déplacer en tant que \code{YOU}, les touches sont : \code{UP}, \code{DOWN}, \code{LEFT}, \code{RIGHT} et pour recommencer un niveau la touche est \code{R}. Cela peut être modifiable dans le menu \code{Paramètre}.

\noindent En coopération pour se déplacer en tant que \code{P1}, les touches sont les mêmes que \code{YOU}. Pour le \code{P2}, les touches sont : \code{T},  \code{G}, \code{F},  \code{H}, ces touches ne sont pas modifiables.

\noindent La touche \code{ESC} permet de retourner au menu.

\subsection{Fichier}
\noindent Les fichiers : \code{achievement}, \code{crash.log}, \code{config.txt} sont créés à la racine du projet au premier lancement du jeu, leur suppression remet tous les paramètres, succès et l'avancement du joueur à zéro.

\subsection{Ant}
\noindent Attention la commande \code{ant clean} supprimera aussi les fichiers : \code{achievement}, \code{BIS.log}, \code{config.txt}.

\noindent Une commande supplémentaire \code{ant dist} premet de crée un fichier Jar.

\pagebreak
\bibliographystyle{unsrt-fr}
\bibliography{biblio}
\end{document}
