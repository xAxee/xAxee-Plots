# xAxee Plots

xAxee Plots to plugin do Minecrafta, który umożliwia graczom zarządzanie swoimi działkami. Dzięki niemu gracze mogą tworzyć, usuwać i zarządzać działkami, a także udzielać innym graczom uprawnienia.

## Funkcje
- Tworzenie i usuwanie działek.
- Dodawanie graczy do działek jako członków lub właścicieli.
- Zarządzanie dostępem do działek.
- Wyświetlanie szczegółowych informacji o działkach.
- Obsługa wizualnego zaznaczania działek (CUI).

## Instalacja
-   Pobierz najnowszą wersję pluginu z [repozytorium GitHub](https://github.com/xAxee/xAxee-Plots/tree/main).
- Umieść plik `.jar` w folderze `plugins` na Twoim serwerze Minecraft.
- Uruchom serwer, aby wygenerować pliki konfiguracyjne.
- Skonfiguruj plugin zgodnie z własnymi potrzebami.

## Komendy
Lista komend dostępnych w pluginie:

### Informacyjne
- **`/teren znacznik 1/2`**  - Zaznacza krawędzie działki.
- **`/teren info [działka]`** - Wyświetla informacje o wybranej działce.
- **`/teren lista`**  - Wyświetla listę wszystkich Twoich działek.

### Zarządzanie działkami
- **`/teren stworz <działka>`** - Tworzy nową działkę.

- **`/teren usun <działka>`**  - Usuwa wybraną działkę.

- **`/teren zaznacz [działka]`** - Zaznacza działkę przy użyciu CUI.

### Uprawnienia graczy
- **`/teren dodaj <działka> <gracz>`** - Dodaje gracza jako członka działki.

- **`/teren wlasciciel <działka> <gracz>`** - Dodaje gracza jako właściciela działki.

- **`/teren wyrzuc <działka> <gracz>`**  - Usuwa gracza z działki.

- **`/teren degraduj <działka> <gracz>`**  - Usuwa gracza z listy właścicieli działki.

## Przykłady użycia
- Zaznacz krawędzie działki: `/teren znacznik 1` i `/teren znacznik 2`
- Utwórz nową działkę:  `/teren stworz moja_dzialka`
- Dodaj gracza do działki:  `/teren dodaj moja_dzialka xAxee`
- Ustaw gracza jako właściciela działki:  `/teren wlasciciel moja_dzialka xAxee`
- Usuń działkę:  `/teren usun moja_dzialka`

## Współtworzenie
Jeśli masz pomysł na nową funkcjonalność lub znalazłeś błąd, zgłoś to w sekcji [Issues](https://github.com/xAxee/xAxee-Plots/issues) lub stwórz pull request.

## Kontakt
Jeśli masz pytania, napisz do mnie na GitHubie lub na serwerze Discord!

**Miłej gry!**
