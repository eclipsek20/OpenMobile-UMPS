# Open Mobile - Universal Mobile Payment System

## Preface

Smartphones have become an integral part of our daily lives. From ordering a taxi and seeking recipe ideas from AI to monitoring our health, they assist us in countless ways. However, using these conveniences often requires multiple apps, which means entrusting our personal information to developers who may not always prioritize privacy.  

The broad goal of this project is to empower individuals by offering them the choice to use truly [free](https://www.gnu.org/philosophy/free-sw.html) software. Unlike proprietary systems that restrict freedom (e.g., [SafetyNet](https://en.wikipedia.org/wiki/SafetyNet)), Open Mobile aims to retain the comforts of the digital bubble while respecting users' rights to privacy and control.

## General Overview

The Universal Mobile Payment System (UMPS for short) seeks to make mobile payments as simple and intuitive as using Google Pay or Apple Pay while ensuring universal support, robust security, and adherence to the principles of [free](https://www.gnu.org/philosophy/free-sw.html) software.


## Implementation Types

One of the main challenges with this project (and similar ones) is that financial institutions are understandably cautious when adopting new technologies. Fortunately, in the UK and EU, PSD2 (Revised Payment Services Directive) opens exciting opportunities for innovation. However, this framework does not cover the global market. Below are potential implementation strategies for this project:

### **1. NFC with PSD2 SEPA/Local Transfers (Current Vision)**

This approach leverages PSD2-compliant services for SEPA (Single Euro Payments Area) or local transfers, utilizing either NFC or QR codes for user-friendly payment interactions, this is the most likely approach, but a third-party certified service provider would be needed. 

The fundamental principle involves leveraging HCE (Host Card Emulation) to facilitate payments, with data transmitted via NFC in the following flow:

Device (HCE) <==> NFC <==> Terminal

The bank-end processing would look something like this:

Device <==> Negoication of Type of Payment, details etc. <==> Terminal
Device Backend ==> PSD2 Bank Backend ==> Bank Transfer initiation ==> Negociation Server ==> Notification of Bank Transfer
Terminal Backend ==> PSD2 Negociation Server ==> Await Transfer Success

This would require the use of either same-bank, SEPA or other instant transfer providers (like ELIXIR in Poland)



### **2. NFC with Card Issuer Partner**

In my [own opinion](https://www.github.com/eclipsek20), this option is *highly* unlikely in the near future unless new regulations are introduced. As described in the preface, financial institutions are often reluctant to embrace disruptive technologies without strong incentives.  

However, if achievable, this implementation would closely resemble Google Pay or Apple Pay. [Personally](https://www.github.com/eclipsek20), I find this to be one of the most promising approaches.

Similarily to above, the fundamental principle involves leveraging HCE (Host Card Emulation) to facilitate payments:

Device (HCE) <==> NFC <==> Terminal


After data transmission, the payment process would proceed via secure backend systems, similar to existing mobile payment platforms so:

Terminmal <===> Backend <===> Card Issiuer <===> Bank

## Technical Description

TBD

## Challenges and Opportunities

- **Financial Provider woes:** Financial Providors are known to be very conservative (and rightfully so) to new and innovative technologies, which makes this project even more ambitious.
- **Global Expansion:** Addressing markets outside of PSD2 jurisdiction is very tricky and almost impossible without political change or large capital.
- **Large players don't talk small:** There is very little posibilities for there to be a meaningfull partnership between key players and this project, which means this project has to rely on existing legal frameworks in order to be plausible



[I](https://www.github.com/eclipsek20) use this README as the anchor point for this project and as such it will evolve as to accomidate the project.