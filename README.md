Proyecto HSV y CMYK

Manipulación y simulación de color mediante RGB, CMYK y HSV

Proyecto académico desarrollado en Java utilizando Apache NetBeans, con el objetivo de aplicar y comparar diferentes modelos de color mediante el procesamiento de imágenes digitales.

La aplicación permite cargar una imagen original, generar una simulación basada en el modelo CMYK y aplicar un filtro de modificación de saturación utilizando el modelo HSV/HSB.

---

1. Descripción del proyecto

El proyecto consiste en una aplicación de escritorio desarrollada en Java que procesa una imagen píxel por píxel.

A partir de una imagen original se generan dos resultados:

- "resultado_cmyk.jpg" → simulación del procesamiento mediante CMYK.
- "resultado_hsv.jpg" → resultado de aumentar la saturación utilizando HSV/HSB.

De esta manera se pueden comparar visualmente:

1. La imagen original.
2. La imagen procesada mediante una simulación CMYK.
3. La imagen procesada mediante HSV.

---

2. Objetivo

El objetivo principal es comprender cómo diferentes modelos de color representan y manipulan una imagen.

También se busca comprobar que una modificación como el aumento de saturación resulta más intuitiva cuando se trabaja con HSV, ya que este modelo separa directamente el matiz, la saturación y el brillo.

---

3. Tecnologías utilizadas

- Java
- Apache NetBeans
- Java with Ant
- Java Application
- Java Swing
- Git
- GitHub

---

4. Modelos de color utilizados

RGB

RGB significa:

- R = Red (Rojo)
- G = Green (Verde)
- B = Blue (Azul)

Es un modelo de color aditivo, ya que combina luz roja, verde y azul para producir diferentes colores.

RGB es utilizado principalmente en:

- Monitores.
- Pantallas de computadoras.
- Teléfonos celulares.
- Televisores.
- Cámaras y dispositivos digitales.

En este proyecto, la imagen original se procesa inicialmente utilizando sus componentes RGB.

---

CMYK

CMYK significa:

- C = Cyan (Cian)
- M = Magenta
- Y = Yellow (Amarillo)
- K = Key/Black (Negro)

Es un modelo de color sustractivo, utilizado principalmente en procesos de impresión.

En el programa se realiza una simulación computacional del proceso CMYK:

1. Se obtienen los valores RGB de cada píxel.
2. Se normalizan los valores.
3. Se convierten de RGB a CMYK.
4. Se realiza una cuantización de los componentes CMYK para representar una reproducción simplificada.
5. Los valores CMYK se convierten nuevamente a RGB.
6. Se genera el archivo "resultado_cmyk.jpg".

---

HSV

HSV significa:

- H = Hue (Matiz)
- S = Saturation (Saturación)
- V = Value (Valor o brillo)

Java utiliza internamente el nombre HSB (Hue, Saturation, Brightness) para una representación equivalente.

Este modelo resulta muy útil cuando se desea modificar características específicas de un color.

En este proyecto se modifica principalmente la componente S, correspondiente a la saturación.

---

5. Manipulación realizada con HSV

La manipulación específica realizada consiste en:

«Aumentar la saturación de todos los píxeles de la imagen aproximadamente un 50 %.»

El programa utiliza un factor de:

1.5

Por lo tanto:

S nueva = S original × 1.5

Por ejemplo, si un píxel tiene una saturación de:

S = 0.40

se realiza:

0.40 × 1.5 = 0.60

Por lo tanto, la saturación aumenta aproximadamente un 50 %.

El programa también limita la saturación a un máximo de "1.0", para evitar que los valores salgan del rango permitido.

---

6. ¿Por qué utilizar HSV para modificar la saturación?

Modificar la saturación utilizando HSV resulta conceptualmente más intuitivo que modificar directamente los canales RGB.

En RGB no existe un canal específico llamado "saturación". Si se quisiera aumentar la intensidad de los colores modificando directamente R, G y B, sería necesario realizar cálculos sobre los tres canales de manera coordinada.

En cambio, HSV separa las características del color:

H → Matiz
S → Saturación
V → Brillo

Por lo tanto, para aumentar la saturación solamente es necesario trabajar directamente con:

S

En el programa se realiza:

RGB → HSV/HSB → modificar S → RGB

Esto hace que la operación sea más fácil de comprender y más directa para este tipo de manipulación.

---

7. Funcionamiento del filtro HSV

Para cada píxel de la imagen:

1. Se obtiene el valor RGB.
2. Se convierte RGB a HSB/HSV.
3. Se identifica la componente de saturación.
4. Se multiplica la saturación por "1.5".
5. Se limita el resultado a "1.0".
6. Se convierte nuevamente de HSB/HSV a RGB.
7. Se guarda el nuevo color en la imagen procesada.

En Java se utilizan principalmente:

Color.RGBtoHSB()

para convertir de RGB a HSB/HSV, y:

Color.HSBtoRGB()

para regresar nuevamente a RGB.

---

8. ¿Por qué "resultado_cmyk.jpg" se ve diferente?

La imagen "resultado_cmyk.jpg" puede verse diferente a la imagen original y generalmente puede presentar colores más opacos, menos brillantes o ligeramente "apagados".

Esto ocurre porque RGB y CMYK fueron diseñados para propósitos diferentes.

RGB trabaja principalmente con luz, mientras que CMYK trabaja con tintas o pigmentos.

En una pantalla, los colores RGB se producen mediante luz emitida por los píxeles. Esto permite representar colores muy brillantes y saturados.

En impresión, los colores CMYK se producen mediante la combinación de tintas sobre un material físico. Las tintas no pueden reproducir todos los colores luminosos que puede producir una pantalla.

Por esta razón, al realizar una conversión o simulación CMYK pueden perderse algunos colores que estaban disponibles originalmente en RGB.

---

9. Relación con el concepto de Gamut

El gamut representa el conjunto de colores que un determinado sistema puede representar o reproducir.

El espacio de colores RGB utilizado por una pantalla puede contener colores que no pueden reproducirse exactamente mediante un sistema de impresión CMYK.

Por ejemplo, algunos colores:

- Muy brillantes.
- Muy saturados.
- Con apariencia luminosa intensa.

pueden encontrarse dentro del gamut de una pantalla RGB pero fuera del gamut que puede reproducir una impresora CMYK.

Cuando un color RGB se encuentra fuera del gamut CMYK, es necesario aproximarlo a un color que sí pueda representarse dentro del espacio CMYK.

Esto puede provocar que el resultado se vea:

- Más opaco.
- Menos saturado.
- Menos brillante.
- Diferente al original.

Por eso "resultado_cmyk.jpg" no necesariamente tiene exactamente los mismos colores que la imagen RGB original.

---

10. RGB frente a CMYK

Característica| RGB| CMYK
Tipo| Aditivo| Sustractivo
Componentes| Rojo, Verde, Azul| Cian, Magenta, Amarillo, Negro
Principal uso| Pantallas y dispositivos digitales| Impresión
Funcionamiento| Combina luz| Combina tintas
Colores brillantes| Puede representar muchos colores luminosos| Tiene mayores limitaciones físicas
Gamut| Depende del espacio RGB utilizado| Depende de tintas, papel y proceso de impresión

La diferencia entre ambos modelos explica por qué una imagen puede verse diferente cuando se simula una reproducción CMYK.

---

11. Proceso general de la aplicación

El funcionamiento del programa puede representarse de la siguiente manera:

              IMAGEN ORIGINAL
                     │
                     │
                    RGB
                     │
            ┌────────┴────────┐
            │                 │
            ▼                 ▼
         CMYK              HSV/HSB
            │                 │
            │            Modificar S
            │              × 1.5
            │                 │
            ▼                 ▼
     Regresar a RGB       Regresar a RGB
            │                 │
            ▼                 ▼
resultado_cmyk.jpg      resultado_hsv.jpg

---

12. Archivos generados

Después de procesar la imagen, el programa genera:

resultado_cmyk.jpg

Este archivo corresponde al resultado de la simulación CMYK.

También genera:

resultado_hsv.jpg

Este archivo corresponde al resultado del filtro HSV con aumento de saturación.

---

13. Evidencias del proyecto

Para demostrar el funcionamiento de la aplicación se pueden incluir las siguientes evidencias:

Imagen 1 — Imagen base

La imagen original cargada en el programa.

Imagen 2 — Resultado CMYK

Archivo:

resultado_cmyk.jpg

Corresponde a la simulación del modelo CMYK.

Imagen 3 — Resultado HSV

Archivo:

resultado_hsv.jpg

Corresponde al aumento de saturación utilizando HSV/HSB.

También se recomienda incluir capturas de:

- Proyecto abierto en Apache NetBeans.
- Código de "ProyectoHSV.java".
- Código de "VentanaHSV.java".
- Aplicación ejecutándose.
- Botón de procesamiento.
- Carpeta donde se generaron los dos archivos de resultado.

---

14. Estructura del proyecto

ProyectoHSV/
│
├── nbproject/
│
├── src/
│   └── proyectohsv/
│       ├── ProyectoHSV.java
│       └── VentanaHSV.java
│
├── build.xml
├── .gitignore
└── README.md

Las carpetas generadas automáticamente como "build" y "dist" no son necesarias para el repositorio y se excluyen mediante ".gitignore".

---

15. Requisitos para ejecutar el proyecto

Para ejecutar el proyecto se necesita:

- JDK 17 o superior.
- Apache NetBeans.
- Java with Ant.
- Sistema operativo compatible con Java.
- Git, si se desea trabajar con el repositorio.

---

16. Cómo ejecutar el proyecto

Desde Apache NetBeans

1. Abrir Apache NetBeans.
2. Seleccionar File → Open Project.
3. Seleccionar la carpeta "ProyectoHSV".
4. Abrir el proyecto.
5. Ejecutar utilizando Run Project o presionando "F6".
6. Presionar Cargar imagen.
7. Seleccionar una imagen.
8. Dejar la saturación en 150 % para reproducir el resultado utilizado en el proyecto.
9. Presionar Procesar CMYK + HSV.
10. Revisar la carpeta donde se encontraba la imagen original.

Se generarán:

resultado_cmyk.jpg
resultado_hsv.jpg

---

17. Git y GitHub

El proyecto utiliza Git para controlar las versiones del código y GitHub para almacenar y compartir el repositorio.

Comandos principales utilizados:

git init

Inicializa el repositorio local.

git add .

Agrega los archivos al área de preparación.

git commit -m "Proyecto HSV y CMYK inicial"

Crea una versión del proyecto.

git branch -M main

Establece "main" como rama principal.

git remote add origin https://github.com/TU-USUARIO/ProyectoHSV.git

Conecta el proyecto local con el repositorio de GitHub.

git push -u origin main

Sube el proyecto al repositorio.

---

18. Actualizar el repositorio

Cuando se realicen cambios en el proyecto:

git add .

Después:

git commit -m "Actualización del proyecto HSV y CMYK"

Y finalmente:

git push

---

19. Conclusión

Este proyecto permitió comprender de manera práctica la diferencia entre los modelos RGB, CMYK y HSV.

RGB es apropiado para representar colores mediante luz en dispositivos como pantallas, mientras que CMYK está orientado principalmente a la reproducción mediante tintas en procesos de impresión. Debido a las diferencias entre sus gamuts, una simulación CMYK puede producir colores más apagados o diferentes respecto a la imagen RGB original.

Por otro lado, HSV resulta especialmente útil para realizar modificaciones de color como el aumento de saturación. En lugar de modificar directamente los canales R, G y B, se puede trabajar directamente sobre la componente S, haciendo que la operación sea más sencilla de comprender y aplicar.

La aplicación desarrollada demuestra estos conceptos mediante el procesamiento de cada píxel y la generación de los archivos "resultado_cmyk.jpg" y "resultado_hsv.jpg".

---

Proyecto académico de Graficación por Computadora.

Proyecto: ProyectoHSV
Lenguaje: Java
IDE: Apache NetBeans
Modelos utilizados: RGB, CMYK y HSV/HSB
