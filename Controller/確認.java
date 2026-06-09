   @GetMapping

    public String init(Model model) {

        model.addAttribute("form", new PurchaseForm());

        return "purchase";

    }

    @PostMapping(params = "search")

    public String search(

            @ModelAttribute("form") PurchaseForm form) {

        // 会員検索

        MemberDto memberDto = memberService.findByCode(

                form.getMemberCode());

        form.setMemberDto(memberDto);

        return "purchase";

    }

    @PostMapping(params = "confirm")

    public String confirm(

            @ModelAttribute("form") PurchaseForm form,

            Model model) {

        MemberDto memberDto = form.getMemberDto();

        String paymentMethod = form.getPaymentMethod();

        model.addAttribute("memberDto", memberDto);

        model.addAttribute("paymentMethod", paymentMethod);

        return "confirm";

    }

